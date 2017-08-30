// Problem 16 Drop every Nth element from a list.
// Example:
//   scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
// res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p16 {
  def main(args: Array[String]): Unit = {
    println(drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  def drop[T](cnt: Int, list: List[T]): List[T] = {
    list match {
      case x :: xs if (cnt > 1) => x :: drop(cnt - 1, xs)
      case x :: xs if (cnt == 1) => xs
      case Nil => Nil
    }
  }
}
