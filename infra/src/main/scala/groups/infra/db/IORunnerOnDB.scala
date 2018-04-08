package groups.infra.db

import groups.support.{IOAction, IORunner, PooledContexts}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class IORunnerOnDB @Inject()(val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with IORunner {

  private implicit val ec: ExecutionContext = PooledContexts.dbContext

  override def run[R](action: IOAction[R]): Future[R] =
    db.run(action.asInstanceOf[IOActionOnDB[R]].action)
}
