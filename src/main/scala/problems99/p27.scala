package problems99
// Problem 27 Group the elements of a set into disjoint subsets.
// a) In how many ways can a group of 9 people work in 3 disjoint subgroups of 2, 3 and 4 persons? Write a function that generates all the possibilities.
//     Example:
//
//     scala> group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
// res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David, Evi), List(Flip, Gary, Hugo, Ida)), ...
// b) Generalize the above predicate in a way that we can specify a list of group sizes and the predicate will return a list of groups.
//
// Example:
//
//     scala> group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
// res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David), List(Evi, Flip, Gary, Hugo, Ida)), ...
// Note that we do not want permutations of the group members; i.e. ((Aldo, Beat), ...) is the same solution as ((Beat, Aldo), ...). However, we make a difference between ((Aldo, Beat), (Carla, David), ...) and ((Carla, David), (Aldo, Beat), ...).
//
// You may find more about this combinatorial problem in a good book on discrete mathematics under the term "multinomial coefficients".
// http://aperiodic.net/phil/scala/s-99/
// https://github.com/dwango/S99/tree/master/src/main/scala/jp/co/dwango/s99

object p27 {
  def main(args: Array[String]): Unit = {
    println(group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida")))
    println(group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida")))
  }

  def group3[T](list: List[T]): List[(List[T], List[T], List[T])] = {
    for {
      groupWith2Elements <- p26.combinations(2, list)
      groupWith3Elements <- p26.combinations(3, list diff groupWith2Elements)
    }
      yield (groupWith2Elements, groupWith3Elements, list diff groupWith2Elements diff groupWith3Elements)
  }

  def group[T](setting: List[Int], list: List[T]): List[List[List[T]]] = {
    def group_(setting: List[Int], n: Int, list: List[T]): List[List[List[T]]] = {
      setting match {
        case Nil => List(Nil)
        case x::xs =>
          groupInto(n, x, list).flatMap { case (s, r) => group_(xs, n - x, r).map(k => k.::(s)) }
      }
    }
    group_(setting, list.size, list)
  }

  def groupInto[T](n: Int, k: Int, list: List[T]): List[(List[T], List[T])] = list match {
    case _ if k == n => List((list, Nil))
    case _ if k == 0 => List((Nil, list))
    case x :: xs if k > 0 =>
      append(
        groupInto(n - 1, k - 1, xs).map{a => (x::a._1, a._2)},
        groupInto(n - 1, k, xs).map{a => (a._1, x::a._2)})
    case _ => error("")
  }

  def append[T](a: List[T], b: List[T]): List[T] = a match {
    case Nil => b
    case x::xs => x::append(xs, b)
  }
}
