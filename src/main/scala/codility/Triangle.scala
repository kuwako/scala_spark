package codility

/*
A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
Triplet (0, 2, 4) is triangular.

Write a function:

object Solution { def solution(a: Array[Int]): Int }
that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.

For example, given array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
the function should return 1, as explained above. Given array A such that:

  A[0] = 10    A[1] = 50    A[2] = 5
  A[3] = 1
the function should return 0.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

*/

object Triangle extends App {
    override def main(args: Array[String]): Unit = {
      println(solution(Array(10, 2, 5, 1, 8, 20)))
      println(solution(Array(10, 50, 2, 1)))
      println(solution2(Array(10, 2, 5, 1, 8, 20)))
      println(solution2(Array(10, 50, 2, 1)))
    }

    // score 75
    def solution(a: Array[Int]): Int = {
      // a + b > c の組を見つける
      for (x <- 0 until a.size - 2) {
        for (y <- x + 1 until a.size - 1) {
          for (z <- y + 1 until a.size) {
            if (check(a(x), a(y), a(z))) {
              println(List(x, y, z))
              return 1
            }
          }
        }
      }

      0
    }

  def check(x: Int, y: Int, z: Int): Boolean = {
    if (x + y > z && x + z > y && y + z > x) true
    else false
  }

  // score 93
  def solution2(a: Array[Int]): Int = {
    val sa = a.sorted
    for (p <- 0 to sa.length - 3) if (sa(p) + sa(p + 1) > sa(p + 2)) return 1
    0
  }
}
