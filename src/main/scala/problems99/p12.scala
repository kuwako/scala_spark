package problems99

// Problem 12 Decode a run-length encoded list.
// Given a run-length code list generated as specified in problem P10, construct its uncompressed version.
// Example:
//
//   scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
// res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p12 {
  def main(args: Array[String]): Unit = {
    println(decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  }

  def decode[T](list: List[(Int, T)]): List[T] = {
    list.flatMap{ case (n, x) => copyN(n, x)}
  }

  def copyN[T](n: Int, x: T): List[T] = n match {
    case m if (m > 0) => x :: copyN(m - 1, x)
    case 0 => Nil
  }
}
