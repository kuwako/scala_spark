object PartAddition{
    def main(args: Array[String]): Unit = {
      println(solution(Array(4, 6, 7, 3, 2, 7)))
    }

    def solution(a: Array[Int]): Int = {
      var res = 0
      for (i <- 0 until a.size) {
        var list = Nil
        for (j <- i + 1 until a.size) {
          // TODO これだとこの後ろに同じ数字があった場合等に抜ける
          if (a(i) < a(j)) {
            list :+ a(j)
          }
        }

        if (!list.isEmpty) {
          res = res + checkNum(list)
        }
      }

      res
    }

    def checkNum(list: List[Int]): Int = {
      0
    }

}
