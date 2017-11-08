package problems99

// Problem 14 Duplicate the elements of a list.
// Example:
//   scala> duplicate(List('a, 'b, 'c, 'c, 'd))
// res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p14 {
  def main(args: Array[String]): Unit = {
    println(duplicate(List('a, 'b, 'c, 'c, 'd)))
  }

  def duplicate[T](list: List[T]): List[T] = {
    list match {
      case x :: xs => x :: x :: duplicate(xs)
      case Nil => Nil
    }
  }
}
