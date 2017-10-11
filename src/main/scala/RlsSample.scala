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
            // 1 ~ CARD_MAXまでの配列を作る
            val cards = 1.to(CARD_MAX).toArray
            val tmp_arr = arr.filter(n => n != arr(index))
            // 他人のカードを見て、自分の選択肢を絞る
            val candidate = cards.map(x => if(tmp_arr.contains(x)) 0 else x)

            if (isMax(candidate, 0)) {
                println(index + ": MAX")
                return
            } else if (isMin(candidate, 0)) {
                println(index + ": MIN")
                return
            } else if (isMid(candidate, 0)) {
                println(index + ": MID")
                return
            } else {
                println(index + ": ?")
            }
        }

        // もし、他人が1 ~ (PERSON_NUM - 1)までを持っていればMAX
        def isMax(candidate: Array[Int], index: Int): Boolean = {
            val a = 1.to(PERSON_NUM - 1 + index)
            // もしcandidateの中にaに一致するものがなければtrue
            val subCandidate = candidate.filter(x => !a.contains(x) && x != 0)

            // 残っている候補とそこからaを引いた候補が同じ中身なら最大
            candidate.filter(x => x != 0).sameElements(subCandidate)
        }

        // もし、他人が(CARD_MAX - PERSON_NUM) ~ CARD_MAXまでを持っていればMIN
        def isMin(candidate: Array[Int], index: Int): Boolean = {
            val a = ((CARD_MAX - PERSON_NUM + 1) + 1 - index).to(CARD_MAX)
            val subCandidate = candidate.filter(x => !a.contains(x) && x != 0)

            candidate.filter(x => x != 0).sameElements(subCandidate)
        }

        // もし、他人がCARD_MAXと1 ~ (PERSON_NUM - 2)までを持っていればMID
        def isMid(candidate: Array[Int], index: Int): Boolean = {
            val a = 1.to(PERSON_NUM - 2 + index)
            val subCandidate = candidate.filter(x => !a.contains(x) && x != 0 && x != CARD_MAX)

            candidate.filter(x => x != 0).sameElements(subCandidate)
        }
        // それ以外ならば ?

        // 二人目以降
        // 1 ~ CARD_MAXまでの配列を作る
        // 他人のカードを見て、自分の選択肢を絞る
        // もし、他人が1 ~ (PERSON_NUM - 1)まで(そのうちの一つが穴あきでも良い)を持っていればMAX
        // もし、他人が(CARD_MAX - PERSON_NUM) ~ CARD_MAXまで(そのうちの一つが穴あきでも良い)を持っていればMIN
        // もし、他人がCARD_MAXと1 ~ (PERSON_NUM - 2)まで(そのうち1つが穴あきでも良い)を持っていればMID

        // TODO 最後にもう一回一人
    }
}
