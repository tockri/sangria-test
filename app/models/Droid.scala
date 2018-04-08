package models

case class Droid(
  id: String,
  name: Option[String],
  friends: List[String],
  appearsIn: List[Episode.Value],
  primaryFunction: Option[String]) extends Character


case class DroidInput(id:Option[String],
                      name:Option[String],
                      friends:List[String],
                      appearsIn: List[String],
                      primaryFunction: Option[String]) {
  def parseAppearsIn: List[Episode.Value] =
    appearsIn.map(_.toUpperCase).flatMap {
      case "NEWHOPE" => Some(Episode.NEWHOPE)
      case "EMPIRE" => Some(Episode.EMPIRE)
      case "JEDI" => Some(Episode.JEDI)
      case _ => None
    }
}