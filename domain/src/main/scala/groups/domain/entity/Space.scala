package groups.domain.entity

import java.time.OffsetDateTime

class SpaceId(value:Long) extends EntityId(value)

object SpaceId {
  val Undef = new SpaceId(0) {
    override val defined: Boolean = false
  }
  val System = new SpaceId(1)

  def apply(value:Long):SpaceId = new SpaceId(value)
}

case class Space(id: SpaceId,
                 name: String,
                 nulabAppsSpaceKey: String,
                 created: OffsetDateTime) extends Entity(id)

object Space {
  def create(name: String,
             nulabAppsSpaceKey: String):Space =
    new Space(SpaceId.Undef, name, nulabAppsSpaceKey, OffsetDateTime.now())
}