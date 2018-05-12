package groups.infra.db.slick

import groups.domain.repository.{IOContext, IOContextProvider}

case class IOContextOnSlick() extends IOContext

object IOContextProviderOnSlick extends IOContextProvider {
  def createIOContext():IOContext = IOContextOnSlick()
}
