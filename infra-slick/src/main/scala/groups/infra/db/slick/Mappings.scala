package groups.infra.db.slick

import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, OffsetDateTime, ZoneOffset}

import groups.domain.entity.{EntityId, SpaceId, UserId}
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.reflect.ClassTag

trait Mappings  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private[db] val MySQLDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  private def conv[ID<:EntityId](apply:Long => ID)(implicit tag:ClassTag[ID]) = MappedColumnType.base[ID, Long](
    m => m.value,
    r => apply(r)
  )

  implicit val timeStampConv = MappedColumnType.base[OffsetDateTime, String](
    m => m.atZoneSameInstant(ZoneOffset.UTC).format(MySQLDateTimeFormatter),
    r => LocalDateTime.parse(r, MySQLDateTimeFormatter).atOffset(ZoneOffset.UTC)
  )

  implicit val userIdConv = conv(UserId.apply)
  implicit val spaceIdConv = conv(SpaceId.apply)
}
