object BasicPractice {

  def main(args: Array[String]): Unit = {
    val loop = 40

    val timer1 = new Timer
    println(getFibonacci(loop).toString())
    timer1.end()
    timer1.report("getFibonacci")

    val timer2 = new Timer
    println(fib(loop).toString())
    timer2.end()
    timer2.report("fib")
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
