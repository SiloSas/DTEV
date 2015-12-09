package shared

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll


@JSExportAll
case class Comment(id: String, title: String, comment: String, userName: String, rate: Int, date: js.Date)
