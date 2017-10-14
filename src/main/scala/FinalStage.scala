import java.util.Scanner;
object FinalStage {
    // 14 <= total <= 30
    // ファーストステージ * 2 各3戦 最低2戦 * 2 = 4 最大6戦
    val FIRST_STAGE_MIN = 4
    val FIRST_STAGE_MAX = 6
    // ファイナルステージ * 2 各6戦(1位が先に1勝持っている) 最低3戦 * 2 = 6 最大12戦
    val FINAL_STAGE_MIN = 6
    val FINAL_STAGE_MAX = 12
    // 日本シリーズ どちらかが4勝するまで続く 最低4戦 = 4
    val JAPAN_SERIES_MIN = 4
    // 上記の試合の合計がtotalになるものをカウントする

    def main(args: Array[String]) = {
        var cin = new Scanner(System.in)
        var num = cin.nextInt
        println("input: " + num)
        cin.close()
        println(solution(num))
    }

    def solution(total: Int): Int = {
        def getCombinationOfGame(_total: Int): List[List[Int]] = {
            var result: List[List[Int]] = Nil

            for (firstStageGame <- FIRST_STAGE_MIN until FIRST_STAGE_MAX) {
                for (finalStageGame <- FINAL_STAGE_MIN until FINAL_STAGE_MAX) {
                    // 日本シリーズの試合数は総合試合数 - ファーストステージ - ファイナルステージ
                    val japanSeries = _total - firstStageGame - finalStageGame
                    if (japanSeries >= JAPAN_SERIES_MIN) {
                        result = result ::: List(List(firstStageGame, finalStageGame, japanSeries))
                    }
                }
            }
            result
        }

        def calcFirstStage(i: Int): Int = {
            // TODO 合計でi試合になるような組み合わせの数を考える
            for (i <- 1 to 3) {
                //
            }
        }

        // TODO
        def getTotalGameCombination(combination: List[Int]): Int = {
            // ファーストステージの組み合わせ計算
            val firstStage = calcFirstStage(combination(0))
            // ファイナルステージの組み合わせ計算
            val finalStage = 1
            // 日本シリーズの組み合わせ計算
            val japanSeries = 1

            firstStage * finalStage * japanSeries
        }


        // 試合数の組を分解する
        val combGame = getCombinationOfGame(total)
        println(combGame)

        var sum = 0
        // それぞれでループを回し、組み合わせを算出
        for (combination <- 0 until combGame.length) {
            sum += getTotalGameCombination(combGame(combination))
        }
        // 組み合わせを合計する

        sum
    }
}
