package groups.infra.db

import groups.support.IOAction
import slick.dbio.{DBIOAction, NoStream}

case class IOActionOnDB[R](action:DBIOAction[R, NoStream, Nothing]) extends IOAction[R] {

}
