import org.apache.spark.ml.feature.HashingTF
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by m_kuwako on 2017/07/29.
  */
object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Judge Spam").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val inputRdd = sc.textFile("data/output.txt")
    val tf = new HashingTF(numFeatures = 100)
  }
}
