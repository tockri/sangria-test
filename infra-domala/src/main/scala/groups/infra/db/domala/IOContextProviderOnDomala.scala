package groups.infra.db.domala

import groups.domain.repository.{IOContext, IOContextProvider}

case class IOContextOnDomala() extends IOContext

object IOContextProviderOnDomala extends IOContextProvider {
  def createIOContext():IOContext = IOContextOnDomala()
}
