/**
  * Created by m_kuwako on 2017/07/16.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.io.{File, PrintWriter}
import java.io.{ FileOutputStream=>FileStream, OutputStreamWriter=>StreamWriter };

import scala.sys.process._

object ConcatFiles {
  def main(args: Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("ConcatFiles").setMaster("local[*]")
    val sc = new SparkContext(conf)
    // 全ファイルの読み出し
    val inputRDD = sc.wholeTextFiles("src/resources/livedoor/*/")
    val encode = "UTF-8"
    var buffer: String = ""
    val fileName = "data/output.txt"
    val append = true

    // 書き込み処理
    val fileOutPutStream = new FileStream(fileName, append)
    val writer = new StreamWriter( fileOutPutStream, encode )

    // 連結と保存
    inputRDD.foreach(rdd => {
      buffer = buffer + rdd._1 + ": " + rdd._2
      println(buffer)
    })

    writer.write(buffer)
    writer.close

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
