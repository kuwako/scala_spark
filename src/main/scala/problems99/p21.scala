package problems99

// Problem 21 Insert an element at a given position into a list.
// Example:
//   scala> insertAt('new, 1, List('a, 'b, 'c, 'd))
// res0: List[Symbol] = List('a, 'new, 'b, 'c, 'd)
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p21 {
  def main(args: Array[String]): Unit = {
    println(insertAt('new, 1, List('a, 'b, 'c, 'd)))
  }

  def insertAt[T](one: T, num: Int, list: List[T]): List[T] =  list match {
    case x :: xs if num == 0 => one :: list
    case x :: xs if num > 0 => x :: insertAt(one, num - 1, xs)
    case _ => sys.error("error")
  }
}
