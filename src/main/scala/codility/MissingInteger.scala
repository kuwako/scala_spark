package codility

/*
This is a demo task.

Write a function:

object Solution { def solution(a: Array[Int]): Int }
that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */

object MissingInteger extends App {
    override def main(args: Array[String]): Unit = {
        println(solution(Array(1, 3, 6, 4, 1, 2)))
        println(solution(Array(1, 2, 3)))
        println(solution(Array(-1, -2, -3)))
        println(solution(Array(-1, 2, -3)))
    }

    // score: 100
    // 配列に存在しない中で最小の正の整数を返す
    def solution(a: Array[Int]): Int = {
        val b = a.filter(_ > 0).distinct.sorted.zipWithIndex
        if (b.length == 0) return 1

        var res: Int = 0
        b.foreach(x => {
            if (x._1 != x._2 + 1 && res == 0) {
                res = x._2 + 1
            }
        })

        if (res == 0) res = b.size + 1
        res
    }
}
