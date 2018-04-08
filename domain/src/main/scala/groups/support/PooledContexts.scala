package groups.support

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import scala.concurrent.ExecutionContext

/**
  * ExecutorContext
  */
object PooledContexts extends LoggerSupport {
  private def newService(threadCount:Int):ExecutorService =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(threadCount))
  private def ctx(service:ExecutorService) = ExecutionContext.fromExecutor(service)

  private val db = newService(4)
  implicit val dbContext:ExecutionContext = ctx(db)

  private val app = newService(4)
  implicit val appContext:ExecutionContext = ctx(app)

  private val service = newService(4)
  implicit val serviceContext:ExecutionContext = ctx(service)

  private val job = newService(1)
  implicit val jobContext:ExecutionContext = ctx(job)

  private val api = newService(4)
  implicit val apiContext:ExecutionContext = ctx(api)

  def shutdown(): Unit = {
    Seq(db, app, service, job, api).foreach {svc =>
      try {
        svc.awaitTermination(Long.MaxValue, TimeUnit.NANOSECONDS)
      } catch {
        case ex:Exception => Logger.warn("termination failed", ex)
      } finally {
        svc.shutdownNow()
      }
    }
  }

}
