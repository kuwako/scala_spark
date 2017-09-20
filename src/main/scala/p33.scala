// For the next section, we're going to take a different tack with the solutions.
// We'll declare a new class, S99Int, and an implicit conversion from regular Ints.
// The arithmetic1 file contains the starting definitions for this section.
// Each individual solution will show the relevant additions to the S99Int class.
// The full class will be given at the end of the section.

// Problem 33 Determine whether two positive integer numbers are coprime.
// Two numbers are coprime if their greatest common divisor equals 1.
// scala> 35.isCoprimeTo(64)
// res0: Boolean = true

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p33 {
  def main(args: Array[String]): Unit = {
    println(33.isCoprimeTo(64))
    println(36.isCoprimeTo(64))
  }

  implicit class RichInt(self: Int) {
    def isCoprimeTo(i: Int): Boolean = p32.gcd(self, i) == 1
  }
}
