// Find the last element of a list.
object p01 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 3, 5, 7 , 9)

    println(list.last)

    println(last(list))
  }

  def last[T](sample: List[T]): T = {
    sample match {
      case v :: Nil => v
      case v :: vs => last(vs)
      case _ => sys.error("out")
    }
  }
}
