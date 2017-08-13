// Problem 3 Find the Kth element of a list.
// By convention, the first element in the list is element 0.
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99
object p03 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 3, 5, 7 , 9)

    println(nth(3, list))
  }

  def nth[T](n: Int, list: List[T]): T = list match {
    case x :: _ if n == 0 => x
    case _ :: xs if n > 0 => nth(n - 1, xs)
    case _ => sys.error("something wrong")
  }
}
