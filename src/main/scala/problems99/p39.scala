package problems99

// Problem 39 A list of prime numbers.
// Given a range of integers by its lower and upper limit, construct a list of all prime numbers in that range.
// scala> listPrimesinRange(7 to 31)
// res0: List[Int] = List(7, 11, 13, 17, 19, 23, 29, 31)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p39 {
  def main(args: Array[String]): Unit = {
    println(listPrimeRange(7 to 31))
  }

  def listPrimeRange(r: Range): List[Int] = {
    r.filter(isPrime(_)).toList
  }

  def isPrime(self: Int): Boolean = {
    val nums = 2 to self
    (self > 1) && nums.takeWhile(_ <= Math.sqrt(self)).forall(self % _ != 0)
  }
}
