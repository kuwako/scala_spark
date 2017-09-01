// Problem 18 Extract a slice from a list.
// Given two indices, I and K, the slice is the list containing the elements from and including the Ith element up to but not including the Kth element of the original list. Start counting the elements with 0.
// Example:
//
//   scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
// res0: List[Symbol] = List('d, 'e, 'f, 'g)
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p18 {
  def main(args: Array[String]): Unit = {
    println(slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  def slice[T](start: Int, last:Int, list: List[T]): List[T] =  list match {
    case x :: xs if last == 0 => Nil
    case x :: xs if start <= 0 => x :: slice(start - 1, last - 1, xs)
    case x :: xs => slice(start - 1, last - 1, xs)
    case _ => sys.error("")
  }
}
