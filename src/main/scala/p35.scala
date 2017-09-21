// Problem 35 Determine the prime factors of a given positive integer.
// Construct a flat list containing the prime factors in ascending order.
//     scala> 315.primeFactors
// res0: List[Int] = List(3, 3, 5, 7)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

import scala.math.sqrt

object p35 {
  def main(args: Array[String]): Unit = {
    println(33.primeFactors)
    println(36.primeFactors)
    println(10.primeFactors)
  }

  implicit class RichInt(self: Int) {
    def primeFactors(): List[Int] = {
      def calcPrimeFactors(i: Int, j: Int): List[Int] = i match {
        case _ if (i < j) => Nil
        case x if (x % j == 0) => j :: calcPrimeFactors(i / j, 2)
        case _ => calcPrimeFactors(i, j + 1)
      }

      calcPrimeFactors(self, 2)
    }
  }
}
