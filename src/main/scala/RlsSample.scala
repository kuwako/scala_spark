/*
Q.「相手の思考を推理する」過程をプログラムで表現してください。

A,B,Cの3人が1～5の5枚のカードを使ってインディアンポーカーをします。
3人は、ランダムに1枚のカードを引いて額にかざします。相手のカードは見えますが、自分のカードは見えません。
この状態で、A->B->Cの順番に、自分が1番大きい（MAX）、自分は2番目に大きい（MID）自分が1番小さい（MIN）、わからない （?）、を答えます。
1人でも答えがわかった場合、そこで終了となります。「わからない」と答えた場合、回答権が次の人に移ります。 Cもわからない場合、再度Aに回答権が移ります。3人ともウソを言ったり、適当に答えてはいけません。
例1）「A=1 B=4 C=5」だった場合、「A => MIN」で終了します。
例2）「A=1 B=2 C=4」だった場合、「A => ?, B => MID」で終了します。
Bは「Aがわからないなら、自分は5ではない」と考えるからです。
以上を踏まえて、
引数で「A=1 B=4 C=5」で実行すると「A =>MIN」を出力
引数で「A=1 B=2 C=4」で実行すると「A =>?, B =>MID」
を出力するようなコマンドラインのプログラムを作成してください。
なお、人数やカードの枚数がパラメーター化されていて、さまざまなケースがシミュレーションできるコードが望ましいです。
*/
object RlsSample {
    val CARD_MAX = 5
    val PERSON_NUM = 3

    def main(args: Array[String]): Unit = {
        println(solution(Array(1, 2, 4)))
    }

    def solution(arr: Array[Int]): Unit = {
        for (index <- 0 until arr.length) {
            var doAnalysis = false
            if (index != 0) doAnalysis = true

            if (calc(index, doAnalysis)) return
        }

        // 1周しても答えが出なかった場合
        calc(0, true)

        def calc(index: Int, doAnalysis: Boolean): Boolean = {
            // 1 ~ CARD_MAXまでの配列を作る
            val cards = 1.to(CARD_MAX).toArray
            val tmp_arr = arr.filter(n => n != arr(index))
            // 他人のカードを見て、自分の選択肢を絞る
            var candidate = cards.map(x => if(tmp_arr.contains(x)) 0 else x)
            if (index != 0) candidate = analysis(candidate)

            if (isMax(candidate, index)) {
                println(index + ": MAX")
                true
            } else if (isMin(candidate, index)) {
                println(index + ": MIN")
                true
            } else if (isMid(candidate)) {
                println(index + ": MID")
                true
            } else {
                println(index + ": ?")
                false
            }
        }


        def analysis(candidate: Array[Int]): Array[Int] = {
            // 今までの結果から更にcandidateを絞り込む
            // 最大から最大 - (人数 - 1)が埋まっているわけじゃない => 最大 ~ (人数 - 1)の間にもし一つだけ空いた要素があるならば、自分はそれではない
            // TODO 雑な方法なのでリファクタ
            if (!candidate.contains(CARD_MAX - 1)) candidate(CARD_MAX - 1) = 0
            if (!candidate.contains(2)) candidate(0) = 0

            candidate
        }

        // もし、他人が1 ~ (PERSON_NUM - 1)までを持っていればMAX
        def isMax(candidate: Array[Int], index: Int): Boolean = {
            if (arr.filter(x => x != arr(index)).max > candidate.filter(x => x != 0).max) return false
            val a = 1.to(PERSON_NUM - 1)
            // もしcandidateの中にaに一致するものがなければtrue
            candidate.sameElements(candidate.filter(x => !a.contains(x)))
        }

        // もし、他人が(CARD_MAX - PERSON_NUM) ~ CARD_MAXまでを持っていればMIN
        def isMin(candidate: Array[Int], index: Int): Boolean = {
            if (arr.filter(x => x != arr(index)).min < candidate.filter(x => x != 0).min) return false

            val a = ((CARD_MAX - PERSON_NUM + 1) + 1).to(CARD_MAX)
            candidate.sameElements(candidate.filter(x => !a.contains(x)))
        }

        // もし、他人がCARD_MAXと1 ~ (PERSON_NUM - 2)までを持っていればMID
        def isMid(candidate: Array[Int]): Boolean = {
            val a = 1.to(PERSON_NUM - 2)
            candidate.sameElements(candidate.filter(x => !a.contains(x) && x != CARD_MAX))
        }
    }
}
