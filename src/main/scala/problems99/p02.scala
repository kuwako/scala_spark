package problems99

// Problem 2 – Find the last but one element of a list
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99
object p02 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 3, 5, 7 , 9)

    println(penultimate(list))
  }

  def penultimate[T](list: List[T]): T = list match {
    case x :: _ :: Nil => x
    case _ :: y :: xs => penultimate(y :: xs)
    case _ => sys.error("something wrong")
  }
}
