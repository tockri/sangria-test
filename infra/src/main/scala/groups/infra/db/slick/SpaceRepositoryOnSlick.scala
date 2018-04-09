package groups.infra.db.slick

import groups.domain.entity.{Space, SpaceId}
import groups.domain.repository.{IOContext, SpaceRepository}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.Future

@Singleton
class SpaceRepositoryOnSlick @Inject()(dbConfigProvider: DatabaseConfigProvider,
                                       tables: Tables)
  extends RepositoryOnSlick(dbConfigProvider) with SpaceRepository {
  import tables._
  import profile.api._

  override def get(id: SpaceId)(implicit io:IOContext): Future[Option[Space]] =
    db.run(Spaces
      .filter(_.id === id)
      .result
      .headOption)

  override def store(obj: Space)(implicit io:IOContext): Future[Space] =
    db.run(Spaces
      .returning(Spaces.map(_.id))
      .into((space, id) =>
        space.copy(id = id)
      ) += obj
    )
}
