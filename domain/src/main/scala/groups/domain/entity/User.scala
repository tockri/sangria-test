package groups.domain.entity

import java.time.ZonedDateTime

class UserId(value:Long) extends EntityId(value)

object UserId {
  val Undef = new UserId(0) {
    override val defined: Boolean = false
  }

  val System = new UserId(1)

  def apply(value: Long): UserId = new UserId(value)
}

case class User(id: UserId,
               role: User.Role,
                name:String,
                spaceId: SpaceId,
                nulabId: String,
                created: ZonedDateTime,
                creatorId: UserId) {

}

object User {
  sealed abstract class Role(val value:Int)
  case object Member extends Role(0)
  case object Admin extends Role(1)
  object Role {
    def apply(value: Int): Role =
      value match {
        case Member.value => Member
        case Admin.value => Admin
      }
  }
}