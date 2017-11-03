import scala.annotation.tailrec

object maxLength {
    def main(args: Array[String]): Unit = {
      println(solution(Array(4, 6, 2, 2, 6, 6, 1)))
    }

    def solution(A: Array[Int]) = {
      var N: Int = A.length
      var arr = Array.ofDim[Int](N, 2)

      for (i <- 0 until N by 1) {
        val target = A(i) - 1

        // Aの中で最初の値をarr(target)(0)に持たせる
        if (arr(target)(0) == 0) {
          arr(target)(0) = i
        }

        // 現在の値をarr(target)(1)に持たせる
        arr(target)(1) = i
      }
      val result = arr.maxBy(x => x(1) - x(0))
      result(1) - result(0)
    }
}
