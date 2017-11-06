package problems99

// Problem 36 Determine the prime factors of a given positive integer (2).
// Construct a list containing the prime factors and their multiplicity.
// scala> 315.primeFactorMultiplicity
// res0: List[(Int, Int)] = List((3,2), (5,1), (7,1))
// Alternately, use a Map for the result.
//
// scala> 315.primeFactorMultiplicity
// res0: Map[Int,Int] = Map(3 -> 2, 5 -> 1, 7 -> 1)
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p36 {
  def main(args: Array[String]): Unit = {
    println(33.primeFactorMultiplicity)
    println(36.primeFactorMultiplicity)
    println(10.primeFactorMultiplicity)
    println(315.primeFactorMultiplicity)
  }

  implicit class RichInt(self: Int) {
    def primeFactorMultiplicity(): Map[Int, Int] = {
      def calcPrimeFactors(i: Int, j: Int): List[Int] = i match {
        case _ if (i < j) => Nil
        case x if (x % j == 0) => j :: calcPrimeFactors(i / j, 2)
        case _ => calcPrimeFactors(i, j + 1)
      }

      val res = calcPrimeFactors(self, 2)
      res.map(x => (x, 1))
          .groupBy(y => y._1)
          .mapValues(_.foldLeft(0)(_ + _._2))
          .toSeq
          .sortBy(_._1)
          .toMap
    }
  }
}
