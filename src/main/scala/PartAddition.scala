object PartAddition{
    def main(args: Array[String]): Unit = {
      println(solution(Array(4, 6, 7, 3, 2, 7)))
    }

    def solution(a: Array[Int]): Int = {
      for (i <- 0 until a.size) {
        var list = Nil
        for (j <- i+1 until a.size) {
          if (a(i) < a(j)) {
            list :+ a(j)
          }
        }

        if (!list.isEmpty) {
          checkNum(list)
        }
      }
    }

    def checkNum(list: List[Int]): Unit = {
      0
    }

}
