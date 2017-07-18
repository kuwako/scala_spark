/**
  * Created by m_kuwako on 2017/07/16.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object ConcatFiles {
  def main(args: Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("ConcatFiles").setMaster("local[*]")
    val sc = new SparkContext(conf)
    // 全ファイルの読み出し
    val inputRDD = sc.textFile("src/resources/livedoor/*/*.txt")

    // 連結
    val buffer = inputRDD.flatMap(line => line.split(" "))

    // TODO 保存なんか失敗してる
//    buffer.saveAsObjectFile("data/output2.txt")
  }

  def printRDD(filterName: String, rdd: org.apache.spark.rdd.RDD[_]) = {
    println(filterName)

    rdd.foreach {r => {
      println(r)
    }
    }
  }
}
