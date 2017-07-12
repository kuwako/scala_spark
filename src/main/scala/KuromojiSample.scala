/**
  * Created by m_kuwako on 2017/07/13.
  */
import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token

object KuromojiSample {
  val tokenizer = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build

  val tokens = tokenizer.tokenize("むかしむかしおじいいさんとおばあさんが居ました。おじいさんは山に芝刈りに、おばあさんは川に洗濯に行きました。").toArray

  tokens.foreach { t =>
    val token = t.asInstanceOf[Token]
    println(s"${token.getSurfaceForm} - ${token.getAllFeatures}")
  }
}
