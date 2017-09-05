// Problem 22 Create a list containing all integers within a given range.
// Example:
//   scala> range(4, 9)
// res0: List[Int] = List(4, 5, 6, 7, 8, 9)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p22 {
  def main(args: Array[String]): Unit = {
    println(range(4, 9))
  }

  def range(start: Int, end: Int): List[Int] = start match {
    case x if x < end => x :: range(x + 1, end)
    case x if x == end => x :: Nil
    case _ => sys.error("error")
  }
}
