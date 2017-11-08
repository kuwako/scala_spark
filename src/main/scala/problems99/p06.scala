package problems99

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
    val list_pal = List(1, 2, 3, 2, 1)

    println(isPalidrome(list))
    println(isPalidrome(list_pal))
  }

  def isPalidrome[T](list: List[T]): Boolean = {
    list == p05.reverse(list)
  }
}
