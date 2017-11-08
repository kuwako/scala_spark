package problems99

// Problem 24 Lotto: Draw N different random numbers from the set 1..M.
// Example:
//   scala> lotto(6, 49)
// res0: List[Int] = List(23, 1, 17, 33, 21, 37)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p24 {
  def main(args: Array[String]): Unit = {
    println(lotto(6, 49))
  }

  def lotto(cnt: Int, max: Int): List[Int] = {
    p23.randomSelect(cnt, p22.range(1, max))
  }
}
