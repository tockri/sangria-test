package groups.infra.db.domala

import domala.Required
import domala.jdbc.{Config, DaoProvider}
import groups.domain.entity.{Space, SpaceId}
import groups.domain.repository.{IOContext, SpaceRepository}
import groups.infra.db.domala.dao.{ID, SpaceDao, SpaceEntity}
import groups.support.PooledContexts
import javax.inject.Inject

import scala.concurrent.Future

class SpaceRepositoryOnDomala @Inject()(implicit config: Config)
  extends SpaceRepository {
  implicit val ec = PooledContexts.dbContext
  lazy val spaceDao = DaoProvider.get[SpaceDao](config)

  override def get(id: SpaceId)(implicit io:IOContext): Future[Option[Space]] =
    Future(Required(spaceDao.selectById(ID(id.value))).map(_.space))

  override def store(obj: Space)(implicit io:IOContext): Future[Space] =
    Future(Required{
      (obj.id match {
        case SpaceId.Undef => spaceDao.insert(SpaceEntity(obj))
        case _ => spaceDao.update(SpaceEntity(obj))
      }).entity.space
    })
}
