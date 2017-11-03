import scala.annotation.tailrec

object fizzbuzz {
    def main(args: Array[String]): Unit = {
        val max = 105
//        println(fizzbuzz(1, max,  Nil))
      println(solution(max))
    }

    @tailrec
    def fizzbuzz(cnt: Int, max: Int, buff: List[String]): List[String] = cnt match {
        case x if cnt > max => buff
        case x if cnt % 15 == 0 => fizzbuzz(x + 1, max, buff :+ "fizzbuzz")
        case x if cnt % 3 == 0 => fizzbuzz(x + 1, max, buff :+ "fizz")
        case x if cnt % 5 == 0 => fizzbuzz(x + 1, max, buff :+ "buzz")
        case x => fizzbuzz(x + 1, max, buff :+ x.toString)
        case _ => sys.error("something wrong")
    }

    def  solution(n: Int) = {
        // write your code in Scala 2.12
        val rng = 1 to n

        rng.foreach(x => {
            var str = ""
            if (x % 3 == 0) str = str + "Fizz"
            if (x % 5 == 0) str = str + "Buzz"
            if (x % 7 == 0) str = str + "Woof"
            if (str == "") str = x.toString

            println(str)


        })
    }
}
