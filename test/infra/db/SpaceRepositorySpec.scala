package infra.db

import domain.support.FutureSupport
import groups.domain.entity.{Space, SpaceId}
import groups.domain.repository.{IOContext, SpaceRepository}
import org.scalatest.{DiagrammedAssertions, FlatSpec}
import support.InjectSupport

class SpaceRepositorySpec extends FlatSpec with DiagrammedAssertions with InjectSupport with FutureSupport {

  val spaceRepository = injected[SpaceRepository]
  implicit val io:IOContext = new IOContext {}

  "SpaceRepository" can "get system" in {
    val s = await(spaceRepository.get(SpaceId(1))).get
    assert(s.id == SpaceId.System)
    assert(s.name == "system")
    assert(s.nulabAppsSpaceKey == "")
  }

  "SpaceRepository" can "store nulab" in {
    val s:Space = await(spaceRepository.store(Space.create("nulab", "dummy-space-key")))
    assert(s.name == "nulab")
    assert(s.nulabAppsSpaceKey == "dummy-space-key")
    assert(s.id.value > 1)
  }
}
