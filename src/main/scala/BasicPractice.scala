object BasicPractice {

  def main(args: Array[String]): Unit = {
    println(getFibonacci(20).toString())
  }

  // n 番目のフィボナッチ数を取得する再帰関数を記述せよ
  def getFibonacci(n: BigInt): BigInt = {
    if (n == 0 || n == 1) {
      n
    } else {
      getFibonacci(n - 2) + getFibonacci(n - 1)
    }
  }
}
