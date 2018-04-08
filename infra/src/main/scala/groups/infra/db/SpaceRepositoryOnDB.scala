package groups.infra.db

import groups.domain.entity.{Space, SpaceId}
import groups.domain.repository.SpaceRepository
import groups.support.IOAction
import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider

@Singleton
class SpaceRepositoryOnDB @Inject()(dbConfigProvider: DatabaseConfigProvider,
                                    tables: Tables)
  extends RepositoryOnDB(dbConfigProvider) with SpaceRepository {
  import tables._
  import profile.api._

  override def get(id: SpaceId): IOAction[Option[Space]] =
    IOActionOnDB(Spaces
      .filter(_.id === id)
      .result
      .headOption)

  override def store(obj: Space): IOAction[Space] =
    IOActionOnDB(Spaces
      .returning(Spaces.map(_.id))
      .into((space, id) =>
        space.copy(id = id)
      ) += obj
    )

}
