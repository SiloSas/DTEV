package shared


case class Comment(id: String,
                   comment: String,
                   userName: String,
                   rate: Int,
                   date: String,
                   isValidated: Boolean = false)