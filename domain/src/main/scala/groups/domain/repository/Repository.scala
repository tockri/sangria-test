package groups.domain.repository

import groups.domain.entity.{Entity, EntityId}

import scala.concurrent.Future

trait Repository[E<:Entity[ID], ID<:EntityId] {
  def get(id:ID)(implicit io:IOContext):Future[Option[E]]
//  def list(condition:E => Boolean = _ => true):Future[List[E]]
  def store(obj:E)(implicit io:IOContext):Future[E]
}
