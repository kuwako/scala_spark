package problems99

// For the next section, we're going to take a different tack with the solutions.
// We'll declare a new class, S99Int, and an implicit conversion from regular Ints.
// The arithmetic1 file contains the starting definitions for this section.
// Each individual solution will show the relevant additions to the S99Int class.
// The full class will be given at the end of the section.

// Problem 31 Arithmetic
// Determine whether a given integer number is prime.
//   scala> 7.isPrime
// res0: Boolean = true
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p31 {
  def main(args: Array[String]): Unit = {
    println(7.isPrime)
    println(8.isPrime)
  }

  implicit class RichInt(self: Int) {
    def isPrime(): Boolean = {
      val nums = 2 to self
      (self > 1) && nums.takeWhile(_ <= Math.sqrt(self)).forall(self % _ != 0)
    }
  }
}
