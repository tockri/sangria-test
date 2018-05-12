package groups.domain.entity


/**
  * ID (Long型)
  */
abstract class EntityId(val value:Long) extends Serializable {
  val defined = value > 0

  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case other:EntityId if other.getClass == getClass => other.value == value
      case _ => false
    }
  }
  override def hashCode(): Int = value.hashCode()

  override def toString: String = value.toString
}


/**
  * エンティティ
  */
abstract class Entity[ID<:EntityId](val entityId:ID) {
  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case other:Entity[ID] => other.entityId == entityId
      case _ => false
    }
  }

  override def hashCode(): Int = entityId.hashCode()
}
