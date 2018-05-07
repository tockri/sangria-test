package modules

import com.google.inject.AbstractModule
import groups.domain.repository.SpaceRepository
import groups.infra.db.domala.{DomalaConfig, SpaceRepositoryOnDomala}
import groups.infra.db.slick.SpaceRepositoryOnSlick
import net.codingwell.scalaguice.ScalaModule
import org.seasar.doma.jdbc.dialect.Dialect
import play.api.{Configuration, Environment}

class GroupsModule(environment: Environment,
                   configuration: Configuration) extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bindForDomala()
  }

  private def bindForSlick: Unit = {
    bind[SpaceRepository].to[SpaceRepositoryOnSlick]
  }

  private def bindForDomala(): Unit = {
    bind[domala.jdbc.Config].to[DomalaConfig]
    bind[SpaceRepository].to[SpaceRepositoryOnDomala]
    bind[Dialect].toInstance(getDialect(environment, configuration, "default"))
  }

  private def getDialect(env: Environment, conf: Configuration, dbName: String): Dialect = {
    val key = s"db.$dbName.doma.dialect"
    val dialectClassName = conf.get[String](key)
    Class
      .forName(
        dialectClassName,
        false,
        env.classLoader)
      .newInstance()
      .asInstanceOf[Dialect]
  }
}
