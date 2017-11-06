package problems99

import scala.annotation.tailrec

// Problem 5  Reverse a list.
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99
object p05 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 3, 5, 7 , 9, 11, 13)

    println(reverse(list))
  }

  @tailrec
  def reverse[T](list: List[T], buffer: List[T] = Nil): List[T] = list match {
    case Nil => buffer
    case x :: xs => reverse(xs, x :: buffer)
    case _ => sys.error("something wrong")
  }
}
