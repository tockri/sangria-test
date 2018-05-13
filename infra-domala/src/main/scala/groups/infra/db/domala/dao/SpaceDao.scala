package groups.infra.db.domala.dao

import java.time.{LocalDateTime, ZoneOffset}

import domala._
import domala.jdbc.Result
import groups.domain.entity.{Space, SpaceId}

@Entity
@Table(name = "space")
case class SpaceEntity(@Id
                       @GeneratedValue(strategy = GenerationType.IDENTITY)
                       id: Long,
                       name: String,
                       nulabAppsSpaceKey: String,
                       created: LocalDateTime) {
  def space:Space = Space(SpaceId(id), name, nulabAppsSpaceKey, created.atOffset(ZoneOffset.UTC))
}
object SpaceEntity {
  def apply(space:Space):SpaceEntity =
    new SpaceEntity(id = space.id.value,
      name = space.name,
      nulabAppsSpaceKey = space.nulabAppsSpaceKey,
      created = space.created.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime)
}


@Dao
trait SpaceDao {
  @Select("select * from space where id = /* id */''")
  def selectById(id: ID[SpaceEntity]): Option[SpaceEntity]

  @Select("select * from space")
  def selectAll: Seq[SpaceEntity]

  @Insert
  def insert(space: SpaceEntity): Result[SpaceEntity]

  @Update
  def update(space: SpaceEntity): Result[SpaceEntity]

  @Delete
  def delete(space: SpaceEntity): Result[SpaceEntity]


}


