package spark

/**
  * Created by m_kuwako on 2017/07/08.
  */
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

object JudgeSpam{

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Judge Spam").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // TODO 教科書データ作成
    val spam = sc.textFile("data/spam.txt")
    val ham = sc.textFile("data/ham.txt")

    // TODO kuromoji or mecabで形態素解析させる必要あり

    val tf = new HashingTF(numFeatures = 100)
    // Each email is split into words, and each word is mapped to one feature.
    val spamFeatures = spam.map(email => tf.transform(email.split(" ")))
    val hamFeatures = ham.map(email => tf.transform(email.split(" ")))

    val positiveExamples = spamFeatures.map(features => LabeledPoint(1, features))
    val negativeExamples = hamFeatures.map(features => LabeledPoint(0, features))
    val trainingData = positiveExamples ++ negativeExamples
    trainingData.cache() // Cache data since Logistic Regression is an iterative algorithm.

    // Create a Logistic Regression learner which uses the LBFGS optimizer.
    val lrLearner = new LogisticRegressionWithSGD()
    // Run the actual learning algorithm on the training data.
    val model = lrLearner.run(trainingData)

    // Test on a positive example (spam) and a negative one (ham).
    // First apply the same HashingTF feature transformation used on the training data.
    val posTestExample = tf.transform("O M G GET cheap stuff by sending money to ...".split(" "))
    val negTestExample = tf.transform("Hi Dad, I started studying Spark the other ...".split(" "))
    // Now use the learned model to predict spam/ham for new emails.
    println(s"Prediction for positive test example: ${model.predict(posTestExample)}")
    println(s"Prediction for negative test example: ${model.predict(negTestExample)}")

    sc.stop()
  }
}
