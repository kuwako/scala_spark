
// Problem 25 Generate a random permutation of the elements of a list.
// Hint: Use the solution of problem P23.
// Example:
//
//     scala> randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))
// res0: List[Symbol] = List('b, 'a, 'd, 'c, 'e, 'f)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p25 {
  def main(args: Array[String]): Unit = {
    println(randomPermute(List('a, 'b, 'c, 'd, 'e, 'f)))
  }

  def randomPermute[T](list: List[T]): List[T] = {
    p23.randomSelect(list.size, list)
  }
}
