// Problem 13 Run-length encoding of a list (direct solution).
// Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods you've written (like P09's pack); do all the work directly.
// Example:
//
//   scala> encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
// res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p13 {
  def main(args: Array[String]): Unit = {
    println(encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }

  def encodeDirect[T](list: List[T]): List[(Int, T)] = {
    list.flatMap{ case (n, x) => copyN(n, x)}
  }

  def copyN[T](n: Int, x: T): List[T] = n match {
    case m if (m > 0) => x :: copyN(m - 1, x)
    case 0 => Nil
  }
}
