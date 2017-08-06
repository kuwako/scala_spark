class Timer {
  val _start = System.currentTimeMillis()
  var _end: Long = 0L

  def end = {
    _end = System.currentTimeMillis()
  }

  def report(tag: String = "this"): Unit = {
    _end match {
      case 0L => println("You must execute end method.")
      case _ => println("${tag} has spent ${(_start - _end)} seconds")
    }
  }
}
