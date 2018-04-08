package support

import play.api.inject.guice.GuiceApplicationBuilder

import scala.reflect.ClassTag


/**
  * ModuleでInjectされたインスタンスを返す
  */
trait InjectSupport {
  def injected[T](implicit ct: ClassTag[T]): T = InjectSupport.app.injector.instanceOf(ct)
}

object InjectSupport {
  lazy val app = GuiceApplicationBuilder().build()
}