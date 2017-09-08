
// Problem 26 Generate the combinations of K distinct objects chosen from the N elements of a list.
// In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). For pure mathematicians, this result may be great. But we want to really generate all the possibilities.
//     Example:
//
//     scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
// res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...

// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p26 {
    def main(args: Array[String]): Unit = {
        println(combinations(3, List('a, 'b, 'c, 'd, 'e, 'f)))
    }

    // excerpt from http://d.hatena.ne.jp/xef/20130308/p1
    def combinations[T](num: Int, list: List[T]): List[List[T]] = {
        def csR(n: Int, rs: List[T], ls: List[T]): List[List[T]] = {
            (n, ls) match {
                case (0, _)         => List(rs.reverse)
                case (_, Nil)       => Nil
                case (n, h :: tail) => csR(n, rs, tail) ::: csR(n-1, h :: rs, tail)
            }
        }
        csR(num, Nil, list).reverse
    }
}
