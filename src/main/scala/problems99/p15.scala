package problems99

// Problem 15 Duplicate the elements of a list a given number of times.
// Example:
//   scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
// res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
//
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p15 {
  def main(args: Array[String]): Unit = {
    println(duplicateN(4, List('a, 'b, 'c, 'c, 'd, 'e)))
  }

  def duplicateN[T](loop: Int, list: List[T]): List[T] = {
    def _duplicate(_loop: Int, _value: T, _list: List[T]): List[T] = _loop match {
      case x if (x > 0) => _value :: _duplicate(_loop - 1, _value, _list)
      case 0 => Nil
    }

    list match {
      case x :: xs => _duplicate(loop, x, Nil).union(duplicateN(loop, xs))
      case Nil => Nil
    }
  }
}
