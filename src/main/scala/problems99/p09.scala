package problems99

// Problem 9  Pack consecutive duplicates of list elements into sublists.
// If a list contains repeated elements they should be placed in separate sublists.
//   Example:
//
//   scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
// res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p09 {
  def main(args: Array[String]): Unit = {
    println(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }

  def pack[T](list: List[T]): List[List[T]] = {
    list.foldRight(Nil: List[List[T]]) { (e, ls) =>
      ls match {
        case (x @ `e` :: _) :: xs => (e :: x) :: xs
        case _ => List(e) :: ls
      }
    }
  }
}
