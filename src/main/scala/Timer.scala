class Timer {
  val _start = System.currentTimeMillis()
  var _end: Long = 0L
  println("start")

  def end() = {
    _end = System.currentTimeMillis()
  }

  def report(tag: String = "this"): Unit = {
    _end match {
      case 0L => println("You must execute end method.")
      case _ => println(s"${tag} has spent ${(_end - _start) / 1000.0} seconds")
    }
  }
}
