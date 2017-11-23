package codility

import scala.annotation.tailrec

/*
A non-empty zero-indexed array A consisting of N integers is given.
A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

Write a function:

object Solution { def solution(a: Array[Int]): Int }
that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

For example, given array A such that:

A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:

(3, 4) is a slice of A that has sum 4,
(2, 2) is a slice of A that has sum −6,
(0, 1) is a slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).
Assume that:

N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [−1,000,000..1,000,000];
the result will be an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */

// score
object MaxSliceSum {
  def main(args: Array[String]) = {
    println(
      solution(
        Array(3, 2, -6, 4, 0)
      )
    )
  }

  // score 100
  def solution(A: Array[Int]): Int = {
    // （p, q) p =< qでa[p] + a[p+1] + ... + a[q]の最大を求める
    @tailrec
    def s(A: Array[Int], ind: Int, maxSlice: Int, max: Int): Int = {
      if (ind >= A.length) {
        max
      } else {
        val slice = math.max(A(ind), maxSlice + A(ind))
        s(A, ind + 1, slice, math.max(max, slice))
      }
    }

    s(A, 1, A(0), A(0))
  }
}
