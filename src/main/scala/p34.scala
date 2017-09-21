// Problem 34 Calculate Euler's totient function phi(m).
// Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m) that are coprime to m.
// scala> 10.totient
// res0: Int = 4

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

import math.sqrt

object p34 {
  def main(args: Array[String]): Unit = {
    println(33.totient)
    println(36.totient)
    println(10.totient)
  }

  implicit class RichInt(self: Int) {
    def totient(): Int = {
      def calcToitent(i: Int): Int = i match {
        case x if (x > sqrt(self)) => 0
        case x if (self % x == 0) => 1 + calcToitent(i + 1)
        case _ => calcToitent(i + 1)
      }

      val sum: Int = calcToitent(1)
      sum * 2
    }
  }
}
