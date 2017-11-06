package prac

/*
integer v lies strictry between integers u and w if u < v < w or if u > v > w
a pair of indeces (p, q), where 0 < p < q < n, is said to have adjacent values if no value in the array lies stritry between valus a[p] and a[q], and addition a[p] = a[q]

 */
object MyChallenge {
    def main(args: Array[String]): Unit = {
        println(solution(Array(1, 4, 7, 3, 3, 5)))
    }

    def solution(a: Array[Int]): Int = {
        // write your code in Scala 2.12
        // emptyチェック必要なし
        // 配列の中で自分との距離が最小(>0)のもの
        // return distanceが最大のものをもってくる
        // distanceは値同士の距離ではなく、キーの距離

        val max = 40000
        var num = max




        // 適用できるものがなければ-1を返す
        if (num == max) -1
        else num
    }
}
