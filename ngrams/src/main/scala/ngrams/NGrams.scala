package ngrams

class NGrams(val corpus: Iterator[String]) {
 
  val sentenceStart = "<s>"
  val sentenceEnd = "</s>"
 
  lazy val words = tokenize.toList
  
  def sanitize(words: Iterable[String]): Iterable[String] =
    words.filterNot(word => word.isEmpty)
         .map(word => word filter (char => char.isLetter))
 
  def tokenize: Iterable[String] =
    corpus.filterNot(line => line.isEmpty).mkString(" ")
          .split('.')
          .map(sentence => Array(sentenceStart) ++ sanitize(sentence.split(' ')) ++ Array(sentenceEnd))
          .flatten
          .filterNot(word => word.isEmpty)
 
  def sortNGrams(ngrams: Seq[Any]) =
    ngrams.groupBy(x => x)
             .map { case (key,items) => (key, BigDecimal(items.size.toDouble / words.size)
                                                        .setScale(6, BigDecimal.RoundingMode.HALF_UP).toDouble) }
         	 .toSeq
             .sortBy(_._2)
             .reverse
               
  def unigrams: Seq[(Any, Double)] =
    sortNGrams(words.filterNot(_ == sentenceEnd)
    			    .filterNot(_ == sentenceStart))
 
  def bigrams: Seq[(Any, Double)] = {
    val firstPair = (words.head,sentenceStart)
    val bigrams = 
      words.drop(1)
      	   .foldLeft(List(firstPair))((acc, word) => {
      	     val (previousWord, _) = acc.last
             acc :+ (word,previousWord)
    })
    sortNGrams(bigrams.filterNot { 
      case (word, history) => word == sentenceStart && history == sentenceEnd })
  }
}