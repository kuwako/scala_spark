package codility

object Password{
    def main(args: Array[String]): Unit = {
      println(solution("aaB09A"))
    }

    def solution(s: String): Int = {
      var result = -1
      var str = ""

      s.foreach(x => {
        // 数値だったらそこで区切り
        if (x.isDigit) {
          if (checkStr(str) && str.length > result) {
            result = str.length
          }
          str = ""
        } else {
          str = str + x
        }
      })

      // 配列の最後のstrについて評価できないので最後に再評価
      if (checkStr(str) && str.length > result) {
        result = str.length
      }

      result
    }

    // 大文字が少なくとも一つ含まれているか
    def checkStr(target: String): Boolean = {
      target.foreach(y => {
        if (y.isUpper) return true
      })
      false
    }

}
