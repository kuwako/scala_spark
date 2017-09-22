/* SimpleApp.scala */

object foldLeftPrac {
  def main(args: Array[String]) {
    val keywords = Map("foo" -> "bar", "baz" -> "qux")
    val text = "foo is baz."
    val changedText = keywords.foldLeft(text){ (tex, map) => tex.replace(map._1, map._2) }
    println(changedText)


    val xs = List(1, 2, 3, 4, 5)
    var sum = 0
    for (x <- xs) {
      sum = sum + x
    }
    println(sum)

    xs.foldLeft(0) {(sum, x) => sum + x }
    println(sum)
  }
}
