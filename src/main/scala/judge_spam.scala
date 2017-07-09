/**
  * Created by m_kuwako on 2017/07/08.
  */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD

class judge_spam {
  val conf = new SparkConf().setAppName("JudgeSpam").setMaster("local[*]")
  val sc = new SparkContext(conf)
  val spam = sc.textFile("spam.txt")
  val normal = sc.textFile("normal.txt")

  val tf = new HashingTF(numFeatures = 10000)
  val spamFeatures = spam.map(email => tf.transform(email.split(" ")))
}
