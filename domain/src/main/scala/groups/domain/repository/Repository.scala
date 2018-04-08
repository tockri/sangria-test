package groups.domain.repository

import groups.domain.entity.{Entity, EntityId}
import groups.support.IOAction

trait Repository[E<:Entity[ID], ID<:EntityId] {
  def get(id:ID):IOAction[Option[E]]
//  def list(condition:E => Boolean = _ => true):Future[List[E]]
  def store(obj:E):IOAction[E]
}
