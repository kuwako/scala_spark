/*
A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.

You are given a zero-indexed array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.

The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.

For example, you are given integer X = 5 and array A such that:

  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.

Write a function:

object Solution { def solution(x: Int, a: Array[Int]): Int }
that, given a non-empty zero-indexed array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.

If the frog is never able to jump to the other side of the river, the function should return −1.

For example, given X = 5 and array A such that:

  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
the function should return 6, as explained above.

Assume that:

N and X are integers within the range [1..100,000];
each element of array A is an integer within the range [1..X].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(X), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
object FrogRiverOne {
    def main(args: Array[String]): Unit = {
        println(solution2(5, Array(1, 3, 1, 4, 2, 3, 5, 4))) // require 6
        println(solution2(5, Array(1, 3, 1, 4, 3, 5, 4))) // require -1
        println(solution2(5, Array(1, 3, 1, 2, 2, 3, 5, 4))) // require 7
    }

    // 1 ~ xまでの全ての数字が揃ったときのindexを返す必要がある
    def solution(x: Int, a: Array[Int]): Int = {
        val ans = 1.to(x).toArray

        calc(0, a, Array.emptyIntArray,ans)
    }

    def calc(i: Int, leaves: Array[Int], buff: Array[Int], ans: Array[Int]): Int = {
        val tmp = buff :+ leaves.head
        val tmp2 = tmp.distinct.sorted

        if (tmp2.sameElements(ans)) i
        else if (leaves.tail.isEmpty) -1
        else calc(i + 1, leaves.tail, tmp2, ans)
    }


    def solution2(X: Int, A: Array[Int]): Int = {
        val pos: Array[Int] = Array.ofDim(X + 1)

        def areAllLeavesInPlace = {
            def rec(i: Int): Boolean = {
                if (i == X + 1) true
                else if (pos(i) == 0) false
                else rec(i + 1)
            }
            rec(1)
        }

        def findTime(step: Int, L: List[Int]): Int = {
            if (L.isEmpty) -1
            else {
                pos(L.head) = pos(L.head) + 1
                if (areAllLeavesInPlace) step
                else findTime(step + 1, L.tail)
            }
        }

        findTime(0, A.toList)
    }
}
