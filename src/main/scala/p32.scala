// For the next section, we're going to take a different tack with the solutions.
// We'll declare a new class, S99Int, and an implicit conversion from regular Ints.
// The arithmetic1 file contains the starting definitions for this section.
// Each individual solution will show the relevant additions to the S99Int class.
// The full class will be given at the end of the section.

// Problem 32 Use Euclid's algorithm.
// scala> gcd(36, 63)
// res0: Int = 9

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p32 {
  def main(args: Array[String]): Unit = {
    println(gcd(36, 63))
  }

  def gcd(i: Int, j: Int): Int = {
    if (i == 0 || j == 0) 0
    else euclideanAlgorithm(i, j)
  }

  def euclideanAlgorithm(i: Int, j: Int): Int = {
    if (i > j) {
      if (i % j == 0) j
      else euclideanAlgorithm(i, i % j)
    } else {
      if (j % i == 0) i
      else euclideanAlgorithm(j, j % i)
    }
  }
}
