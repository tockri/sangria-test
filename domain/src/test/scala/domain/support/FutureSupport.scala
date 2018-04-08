package domain.support

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

trait FutureSupport {
  implicit val ec = ExecutionContext.global
  def await[A](f:Future[A]):A = Await.result(f, Duration.Inf)
}
