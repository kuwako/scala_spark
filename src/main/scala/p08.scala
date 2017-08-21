// Problem 8  Eliminate consecutive duplicates of list elements.
// If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
//   Example:
//
//   scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
// res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p08 {
  def main(args: Array[String]): Unit = {
    println(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }

  def compress[T](list: List[T]): List[T] = {
    def compress_[T](pre: T, rest: List[T]): List[T] = rest match {
      case x :: xs if x == pre => compress_(pre, xs)
      case x :: xs => pre :: compress_(x, xs)
      case Nil => pre :: Nil
    }

    list match {
      case x :: xs => compress_(x, xs)
      case Nil => Nil
    }
  }
}
