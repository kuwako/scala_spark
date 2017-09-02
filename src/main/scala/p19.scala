// Problem 19  Rotate a list N places to the left.
// Examples:
//   scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
// res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
//
// scala> rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
// res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p19 {
  def main(args: Array[String]): Unit = {
    println(rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
    println(rotate(-3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  def rotate[T](num: Int, list: List[T]): List[T] =  {
    def _rotate[T](_num: Int, _buff: List[T], _list: List[T]): List[T] = _list match {
      case x :: xs if _num > 0 => _rotate(_num - 1, x :: _buff, xs)
      case _ if _num < 0 => _rotate(_list.length + _num , _buff, _list)
      case _ if _num == 0 => _list ::: _buff.reverse
      case _ => sys.error("error")
    }

    list match {
      case x :: xs => _rotate(num, Nil, list)
      case _ => Nil
    }

  }
}
