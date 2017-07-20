/**
  * Created by m_kuwako on 2017/07/16.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.io.File
import scala.sys.process._

object ConcatFiles {
  def main(args: Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("ConcatFiles").setMaster("local[*]")
    val sc = new SparkContext(conf)
    // 全ファイルの読み出し
    val inputRDD = sc.textFile("src/resources/livedoor/*/*.txt")
    var buffer: String = ""

    val file = new File("data/output3.txt")
    // 連結と保存
    inputRDD.foreach(rdd =>
      buffer = buffer + rdd
    )
    "echo %s".format(buffer) #>> file!
    
    sc.stop()
  }

  def printRDD(filterName: String, rdd: org.apache.spark.rdd.RDD[_]) = {
    println(filterName)

    rdd.foreach {r => {
      println(r)
    }
    }
  }
}
