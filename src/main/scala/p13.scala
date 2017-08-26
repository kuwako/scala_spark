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
    def _encodeDirect(pre: T, n: Int, rest: List[T]): List[(Int, T)] = {
      rest match {
        case x :: xs if pre == x => _encodeDirect(pre, n + 1, xs)
        case x :: xs => (n, pre) :: _encodeDirect(pre, 1, xs)
        case _ => List((n, pre))
      }
    }

    list match {
      case x :: xs => _encodeDirect(x, 1, xs)
      case _ => List()
    }
  }

}
