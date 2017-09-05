import scala.util.Random
// Problem 23 Extract a given number of randomly selected elements from a list.
// Example:
//   scala> randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
// res0: List[Symbol] = List('e, 'd, 'a)
// Hint: Use the solution to problem P20
//
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p23 {
  def main(args: Array[String]): Unit = {
    println(randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h)))
  }

  def randomSelect[T](cnt: Int, list: List[T]): List[T] = {
    if (cnt > 0) {
      val (xs, x) = p20.removeAt(Random.nextInt(list.size), list)
      x :: randomSelect(cnt - 1, xs)
    } else {
      Nil
    }
  }
}
