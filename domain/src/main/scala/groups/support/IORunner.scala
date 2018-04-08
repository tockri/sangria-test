package groups.support

import scala.concurrent.Future

trait IORunner {
  def run[R](action:IOAction[R]):Future[R]
}
