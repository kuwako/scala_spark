package spark

/**
  * Created by m_kuwako on 2017/07/16.
  */
import java.io.{FileOutputStream => FileStream, OutputStreamWriter => StreamWriter}

import org.apache.spark.{SparkConf, SparkContext}

object ConcatFiles {


  def main(args: Array[String]) :Unit = {
    val fileName = "data/output.txt"
    val append = true
    val encode = "UTF-8"
    val fileOutPutStream = new FileStream(fileName, append)
    val writer = new StreamWriter(fileOutPutStream, encode )

    val conf = new SparkConf().setAppName("ConcatFiles").setMaster("local[*]")
    val sc = new SparkContext(conf)
    println("read files")
    // 全ファイルの読み出し
    val inputRDD = sc.wholeTextFiles("src/resources/livedoor/*")

    println("map start")
    // 連結と保存
    val reduced = inputRDD.flatMap(line => line._2.split(" ")).reduce((x, y) => x + " " + y)
    println("map end")

    writer.write(reduced)
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
