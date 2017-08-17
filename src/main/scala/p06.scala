import scala.annotation.tailrec

// Problem 6 (*) Find out whether a list is a palindrome.
// Example:
//   scala> isPalindrome(List(1, 2, 3, 2, 1))
// res0: Boolean = true
// palindrome = 回文
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p06 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 3, 5, 7 , 9, 11, 13)

    println(isPalidrome(list))
  }

  def isPalidrome[T](list: List[T], buffer: List[T] = Nil): Boolean = list match {
    case _ => sys.error("something wrong")
  }
}
