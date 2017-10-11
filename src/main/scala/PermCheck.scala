/*
A non-empty zero-indexed array A consisting of N integers is given.

A permutation is a sequence containing each element from 1 to N once, and only once.

For example, array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
is a permutation, but array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
is not a permutation, because value 2 is missing.

The goal is to check whether array A is a permutation.

Write a function:

object Solution { def solution(a: Array[Int]): Int }
that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.

For example, given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
the function should return 1.

Given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
the function should return 0.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */

object PermCheck {
    def main(args: Array[String]): Unit = {
        println(solution(Array(2, 3, 1, 5))) // 0
        println(solution(Array(1))) // 1
        println(solution(Array(2))) // 0
        println(solution(Array())) // 0
        println(solution(Array(4, 2, 3, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))) // 1
    }
    def solution(a: Array[Int]): Int = {
        if (a.isEmpty) return 0

        var b = a.sorted

        calc(b, 0)
    }

    def calc(arr: Array[Int], i: Int): Int = arr.head match {
        case x if x == i + 1 && arr.tail.isEmpty => 1
        case x if x == i + 1 => calc(arr.tail, i + 1)
        case _ => 0
    }

}
