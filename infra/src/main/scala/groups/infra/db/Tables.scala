package groups.infra.db

import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import java.time.OffsetDateTime

import groups.domain.entity._

@Singleton
class Tables @Inject()(val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile]
    with Mappings {
  import profile.api._

  private[db] class SpaceTable(tag: Tag) extends Table[Space](tag, "space") {
    def id = column[SpaceId]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def nulabAppsSpaceKey = column[String]("nulab_apps_space_key")
    def created = column[OffsetDateTime]("created")

    def * = (id, name, nulabAppsSpaceKey, created) <> ((Space.apply _).tupled, Space.unapply)
  }

  private[db] val Spaces = TableQuery[SpaceTable]
}
