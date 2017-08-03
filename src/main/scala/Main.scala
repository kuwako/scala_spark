import org.apache.spark.ml.feature.HashingTF
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.convert.WrapAsScala._
import org.apache.spark.mllib.feature.Word2Vec
import com.atilika.kuromoji.ipadic.{Token,Tokenizer}

/**
  * Created by m_kuwako on 2017/07/29.
  */
object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("word2vec prac").setMaster("local[*]")
    val sc = new SparkContext(conf)

//    val inputRdd = sc.textFile("data/output.txt")
    val inputRdd = sc.textFile("data/xaa")

    val input = inputRdd.map(line => {
      val builder = new Tokenizer.Builder()
      val tokenizer = builder.build()
      val tokens = tokenizer.tokenize(line)
      val output : StringBuilder = new StringBuilder()
      tokens.foreach(token => {
        if(token.getAllFeatures().indexOf("名詞") != -1) {
          output.append(token.getSurface)
          output.append(" ")
        }
      })
      output.toString() // return
    }).map(line => line.split(" ").toSeq)

    println("mapping end")
    var index: Int = 0
    input.foreach(node => {
      index += 1
      println(index)
    })
    println(input.count().toString())
//    val Word2Vec = new Word2Vec()
    println("fitting start")
    // TODO fittingに時間がかかりすぎて居るので、実行時にメモリを設定 or 読み込むデータ量を減らす
//    val model = Word2Vec.fit(input)
    println("fitting end")
//    for((synonym, cosineSimilarity) <- model.findSynonyms("mac", 40)) { println(s"$synonym $cosineSimilarity") }
  }
}
