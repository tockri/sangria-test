package groups.domain.repository

trait IOContext {

}

trait IOContextProvider {
  def createIOContext():IOContext
}