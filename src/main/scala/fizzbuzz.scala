import scala.annotation.tailrec

object fizzbuzz {
    def main(args: Array[String]): Unit = {
        val max = 100
        println(fizzbuzz(1, max,  Nil))
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
}
