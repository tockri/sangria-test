package groups.support

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
  *
  */
trait LoggerSupport {
  val Logger = com.typesafe.scalalogging.Logger("default")
}

object FutureWithLog extends LoggerSupport {

  private def withLog[A](message:String)(future:Future[A])(implicit ec:ExecutionContext): Future[A] = {
    future.onComplete {
      case Success(a) => Logger.debug("Success: " + message, a)
      case Failure(t: Throwable) => Logger.warn("Fail: " + message, t)
    }
    future
  }

  def apply[A](message:String)(f:Future[A])(implicit ec:ExecutionContext): Future[A] =
    withLog(message)(f)

}