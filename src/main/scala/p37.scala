// Problem 37 Calculate Euler's totient function phi(m) (improved).
// See problem P34 for the definition of Euler's totient function.
// If the list of the prime factors of a number m is known in the form of problem P36
// then the function phi(m>) can be efficiently calculated as follows: Let [[p1, m1], [p2, m2], [p3, m3], ...]
// be the list of prime factors (and their multiplicities) of a given number m.
// Then phi(m) can be calculated with the following formula:
//   phi(m) = (p1-1)*p1(m1-1) * (p2-1)*p2(m2-1) * (p3-1)*p3(m3-1) * ...
// Note that ab stands for the bth power of a.

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p37 {
  def main(args: Array[String]): Unit = {
    println(phi(315))
  }

  def phi(m: Int): Int = {
    // TODO 求められているアウトプットがわからん...保留
    m
  }

}
