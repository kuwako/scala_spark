package problems99

import scala.annotation.tailrec

// Problem 4 Find the number of elements of a list.
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99
object p04 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 3, 5, 7 , 9, 11, 13)

    println(length(list))
  }

  @tailrec
  def length[T](list: List[T], i: Int = 1): Int = list match {
    case x :: Nil => i
    case _ :: xs => length(xs, i + 1)
    case _ => sys.error("something wrong")
  }
}
