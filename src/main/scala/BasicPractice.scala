object BasicPractice {

  def main(args: Array[String]): Unit = {
    println("start")
    val startA = System.currentTimeMillis()
    println(getFibonacci(1000).toString())
    val endA = System.currentTimeMillis()
    println("getFibonacci spent " + (endA - startA).toString + " second")

    println("start")
    val startB = System.currentTimeMillis()
    println(fib(1000).toString())
    val endB = System.currentTimeMillis()
    println("fib spent " + (endB - startB).toString + " second")
  }

  // n 番目のフィボナッチ数を取得する再帰関数を記述せよ
  def getFibonacci(n: BigInt): BigInt = {
    if (n == 0 || n == 1) {
      n
    } else {
      getFibonacci(n - 2) + getFibonacci(n - 1)
    }
  }

  // フィボナッチ数列模範解答
  def fib(n: BigInt): BigInt = {
    @annotation.tailrec
    def loop(n: BigInt, prev: BigInt, cur: BigInt): BigInt = {
      if (n == 0) prev else loop(n - 1, cur, prev + cur)
    }
    loop(n, 0, 1)
  }
}
