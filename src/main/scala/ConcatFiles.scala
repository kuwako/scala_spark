/**
  * Created by m_kuwako on 2017/07/16.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.io.{File, PrintWriter}

import scala.sys.process._

object ConcatFiles {
  def main(args: Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("ConcatFiles").setMaster("local[*]")
    val sc = new SparkContext(conf)
    // 全ファイルの読み出し
    val inputRDD = sc.wholeTextFiles("src/resources/livedoor/*/")
    var buffer: String = ""

    // 連結と保存
    inputRDD.foreach(rdd => {
      buffer = buffer + rdd
      println(buffer)
    })

    val pw = new PrintWriter("data/output.txt")
    pw.write(buffer)
    pw.close

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
