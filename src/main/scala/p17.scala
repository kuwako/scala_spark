// Problem 17 Split a list into two parts.
// The length of the first part is given. Use a Tuple for your result.
// Example:
//     scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
// res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p17 {
  def main(args: Array[String]): Unit = {
    println(split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  def split[T](n: Int, list: List[T]): (List[T], List[T]) =  list match {
    case xs if n == 0 => (List[T](), xs)
    case x :: xs if n > 0 => split(n - 1, xs) match { case (a, b) => (x :: a, b) }
    case _ => sys.error("")
  }
}
