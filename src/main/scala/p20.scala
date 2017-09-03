// Problem 20 Remove the Kth element from a list.
// Return the list and the removed element in a Tuple. Elements are numbered from 0.
// Example:
//   scala> removeAt(1, List('a, 'b, 'c, 'd))
// res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p20 {
  def main(args: Array[String]): Unit = {
    println(removeAt(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  def removeAt[T](num: Int, list: List[T]): (List[T], T) =  list match {
      case x :: xs if num == 0 => (xs, x)
      case x :: xs if num > 0 => removeAt(num - 1, xs) match {case (ys, y) => (x :: ys, y)}
      case _ => sys.error("error")
    }
}
