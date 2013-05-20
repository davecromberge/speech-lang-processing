package ngrams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NGramsSuite extends FunSuite {
  
  import ngrams._

  trait TestSentences {
    val Marley = Seq("Marley was dead: to begin with.").toIterator
    val Scrooge = Seq("Scrooge! a squeezing, wrenching, grasping, scraping, clutching, covetous, old sinner!").toIterator
    val Clerk  = Seq("The clerk smiled faintly.", "The clerk observed that it was only once a year.").toIterator
    val Humbug = Seq("`Humbug!' said Scrooge; and walked across the room.").toIterator
    val empty = Seq("").toIterator
  }

  test("empty sentence test") {
    new TestSentences {
      assert(new NGrams(empty).unigrams === Seq.empty)
    }
  }
  
  test("tokenization splits sentences by spaces") {
    new TestSentences {
      assert(new NGrams(empty).tokenize === Seq("<s>","</s>"))
      assert(new NGrams(Marley).tokenize === Seq("<s>", "Marley", "was", "dead", "to", "begin", "with", "</s>"))
    }
  }
  
  test("sanitization removes non-letters") {
    new TestSentences {
      assert(new NGrams(empty).sanitize(empty.toSeq) === Stream.empty)
      assert(new NGrams(Humbug).sanitize(Humbug.toSeq) === Stream("HumbugsaidScroogeandwalkedacrosstheroom"))
    }
  }
  
  test("unigram sorting") {
    new TestSentences {
      assert(new NGrams(Clerk).unigrams.take(1).head === ("clerk", 0.111111))
    }
  }
  
  test("bigram sorting") {
    new TestSentences {
      assert(new NGrams(Scrooge).bigrams.take(1).head === (("squeezing", "a"), 0.083333))
    }
  }
}
