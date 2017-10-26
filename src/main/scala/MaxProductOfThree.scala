/*
A non-empty zero-indexed array A consisting of N integers is given.
The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

object Solution { def solution(a: Array[Int]): Int }
that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Assume that:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

*/

import scala.math.abs
object MaxProductOfThree extends App {
    override def main(args: Array[String]): Unit = {
        println(solution(Array(-3, 1, 2, -2, 5, 6)))
        println(solution2(Array(-3, 1, 2, -2, 5, 6)))
        println(solution2(Array(10, 10, 10)))
    }

    // score 44
    def solution(a: Array[Int]): Int = {
        val b = a.sorted.reverse
        b(0) * b(1) * b(2)
    }

    // score 100
    def solution2(a: Array[Int]): Int = {
        val b = a.sorted
        var c: Array[Int] = null
        if (b.size > 6) {
            c = Array(b(0), b(1), b(2), b(b.size - 3), b(b.size - 2), b(b.size - 1)).filter(_ != 0)
            if (c.length < 3) return 0
        } else {
            c = b
        }
        var max = Integer.MIN_VALUE
        val len = c.length

        for(x <- 0 until len - 2) {
            for (y <- x + 1 until len - 1) {
                for (z <- y + 1 until len) {
                    if (max < c(x) * c (y) * c(z)) max = c(x) * c(y) * c(z)
                }
            }
        }

        max
    }
}
