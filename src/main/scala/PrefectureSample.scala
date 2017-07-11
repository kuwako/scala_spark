import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object PrefectureSample {
  def printRDD(filterName: String, rdd: org.apache.spark.rdd.RDD[_]) = {
    println(filterName)

    rdd.foreach {r => {
      println(r)
    }
    }
  }

  def main(args: Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("RddSample").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val inputRDD = sc.textFile("data/KEN_All_ROME.CSV")

    //map 1行ずつ処理させる
    val addresses = inputRDD.map{line =>
      val splited = line.replace("\"", "").split(",")

      var result: Array[String] = null
      if (splited(6) == "IKANIKEISAIGANAIBAAI")
        result = Array(splited(0), splited(4), splited(5))
      else
        result = Array(splited(0), splited(4), splited(5), splited(6))

      result.mkString(" ")
    }

    printRDD("mappedRDD", addresses)

    //filterとunion
    val filtered1 = addresses.filter(line => line.contains("OSAKA")).filter(line => line.contains("AOBADAI"))
    val filtered2 = addresses.filter(line => line.contains("KANAGAWA")).filter(line => line.contains("WAKABADAI"))
    val unioned = filtered1.union(filtered2)

    printRDD("filtered RDD 1", filtered1)
    printRDD("filtered RDD 2", filtered2)
    printRDD("unioned RDD", unioned)

    //flatMap
    val flatmapped = unioned.flatMap(line => line.split(" "))
    printRDD("flatmapped", flatmapped)

    //reduce
    val reduced = flatmapped.reduce((x, y) => x + " " + y)
    println("reduced")
    println(reduced)

    //count
    val count = inputRDD.count
    println("count")
    println(count)

  }
}