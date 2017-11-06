package problems99

// Problem 11 Modified run-length encoding.
// Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as (N, E) terms.
// Example:
//
//     scala> encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
// res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
//
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p11 {
  def main(args: Array[String]): Unit = {
    val packed = p09.pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

    println(encode(packed))
  }

  def encode[T](list: List[List[T]]): List[Any] = {
    list.map(x => x.size match {
        case 1 => x.head
        case _ => (x.size, x.head)
    })
  }
}
