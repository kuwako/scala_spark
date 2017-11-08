package spark

/* SimpleApp.scala */
import org.apache.spark.{SparkConf, SparkContext}

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/Users/m-kuwako/IdeaProjects/scala_test/README.md"
    val conf = new SparkConf().setAppName("Simple Application").setAppName("SOME APP NAME").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    sc.stop()
  }
}
