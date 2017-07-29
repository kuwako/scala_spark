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
    val conf = new SparkConf().setAppName("Judge Spam").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val inputRdd = sc.textFile("data/output.txt")

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

    val Word2Vec = new Word2Vec()
    val model = Word2Vec.fit(input)
    for((synonym, cosineSimilarity) <- model.findSynonyms("mac", 40)) { println(s"$synonym $cosineSimilarity") }
  }
}
