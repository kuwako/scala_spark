package spark

/**
  * Created by m_kuwako on 2017/07/13.
  */
import com.atilika.kuromoji.ipadic.{Token, Tokenizer}

object KuromojiSample extends App {
  val builder = new Tokenizer.Builder()
  val tokenizer = builder.build()

  val tokens = tokenizer.tokenize("むかしむかしおじいさんとおばあさんが居ました。おじいさんは山に芝刈りに、おばあさんは川に洗濯に行きました。").toArray

  tokens.foreach { t =>
    val token = t.asInstanceOf[Token]
    println(s"${token.getSurface} - ${token.getAllFeatures}")
  }
}
