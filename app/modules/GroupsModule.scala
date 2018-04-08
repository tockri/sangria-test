package modules

import com.google.inject.AbstractModule
import groups.domain.repository.SpaceRepository
import groups.infra.db.{IORunnerOnDB, SpaceRepositoryOnDB}
import groups.support.IORunner
import net.codingwell.scalaguice.ScalaModule
import play.api.{Configuration, Environment}

class GroupsModule(environment: Environment,
                   configuration: Configuration) extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bindDB()
  }

  private def bindDB(): Unit = {
    bind[IORunner].to[IORunnerOnDB]
    bind[SpaceRepository].to[SpaceRepositoryOnDB]
  }
}
