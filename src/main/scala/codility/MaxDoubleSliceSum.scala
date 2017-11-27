package codility

/*
A non-empty zero-indexed array A consisting of N integers is given.

A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.

The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

For example, array A such that:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
contains the following example double slices:

double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.

Write a function:

object Solution { def solution(a: Array[Int]): Int }
that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.

For example, given:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
the function should return 17, because no double slice of array A has a sum of greater than 17.

Assume that:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−10,000..10,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */

// score
object MaxDoubleSliceSum {
  def main(args: Array[String]) = {
    println(
      solution(
        Array(3, 2, 6, -1, 4, 5, -1, 2)
      )
    )
  }

  // score 100
  def solution(A: Array[Int]): Int = {
    // x < y < z で　A[x + 1] ... A[y - 1] + A[y + 1] + ... + A[z - 1] の最大値を求める
    val N = A.size
    if (N < 3) return 0
    
    // 左側からのmax sliceを格納  
    val maxEndingL = Array.ofDim[Int](N)
    // 右側からのmax sliceを格納
    val maxEndingR = Array.ofDim[Int](N)

    // 左側から計算した時のmaxSliceを記録
    for (x <- 1 until N - 1) {
      val tmp = maxEndingL(x - 1) + A(x)
      if (tmp < 0) maxEndingL(x) = 0
      else maxEndingL(x) = tmp
    }

    // 右側から計算した時のmaxSliceを記録
    for (z <- N - 2 until 1 by -1) {
      val tmp = maxEndingR(z + 1) + A(z)
      if (tmp < 0) maxEndingR(z) = 0
      else maxEndingR(z) = tmp
    }

    // maxが最大となるyの位置を特定
    var max = 0
    for (y <- 1 until N - 1) {
      var tmp = maxEndingL(y - 1) + maxEndingR(y + 1)
      if (max < tmp) max = tmp
    }

    max
  }

}
