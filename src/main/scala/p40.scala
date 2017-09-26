// Problem 40 Goldbach's conjecture.
// Goldbach's conjecture says that every positive even number greater than 2 is the sum of two prime numbers.
// E.g. 28 = 5 + 23. It is one of the most famous facts in number theory that has not been proved to be correct in the general case.
// It has been numerically confirmed up to very large numbers (much larger than Scala's Int can represent).
// Write a function to find the two prime numbers that sum up to a given even integer.
//     scala> 28.goldbach
// res0: (Int, Int) = (5,23)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p40 {
  def main(args: Array[String]): Unit = {
    println(goldbach(28))
    println(goldbach(80))
    println(goldbach(1112))
  }

  def goldbach(i: Int): (Int, Int) = i match {
    case i if i < 2 => sys.error("argument must be greater than 2 and positive")
    case i if i % 2 != 0 => sys.error("argument must be even")
    case _ => calcGoldbach(i, 2)
  }

  def calcGoldbach(origin: Int, j: Int): (Int, Int) = j match {
    case j if j > origin => sys.error("error")
    case j if !isPrime(j) => calcGoldbach(origin, j + 1)
    case j if isPrime(j) && isPrime(origin - j) => (j, origin - j)
    case j if isPrime(j) => calcGoldbach(origin, j + 1)
  }

  def isPrime(self: Int): Boolean = {
    val nums = 2 to self
    (self > 1) && nums.takeWhile(_ <= Math.sqrt(self)).forall(self % _ != 0)
  }
}
