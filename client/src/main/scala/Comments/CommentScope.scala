package Comments


import com.greencatsoft.angularjs.core.Scope

import scala.scalajs.js

trait CommentScope extends Scope {

  var comments: js.Array[shared.Comment] = js.native

  var newComment: NewComment = js.native

}

trait NewComment extends Scope {
  var id: String = js.native

  var title: String = js.native

  var comment: String = js.native

  var userName: String = js.native

  var rate: Int = js.native
}