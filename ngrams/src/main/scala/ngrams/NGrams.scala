package ngrams
 
class NGrams(val book: Iterator[String]) {
 
  val sentenceStart = "<s>"
  val sentenceEnd = "</s>"
 
  lazy val words = getWords.toList
  def sanitize(words: Seq[String]) =
    words.filterNot(word => word.isEmpty)
              .map(word => word filter (char => char.isLetter))
 
  def getWords =
    book.filterNot(line => line.isEmpty).mkString(" ")
           .split('.')
           .map(sentence => Array(sentenceStart) ++ sanitize(sentence.split(' ')) ++ Array(sentenceEnd))
        .flatten
        .filterNot(word => word.isEmpty)
 
  def sortNGrams(ngrams: Seq[Any]) =
    ngrams.groupBy(x => x)
             .map { case (key,items) => (key,items.size) }
             .toSeq
             .sortBy(_._2)
             .reverse
               
  def unigrams =
    sortNGrams(words)
 
  def bigrams = {
    val firstPair = (words.head,sentenceStart)
    val bigrams = words.drop(1)
      .foldLeft(List(firstPair))((acc, word) => {
        val (previousWord, _) = acc.last
        acc :+ (word,previousWord)
    })
    sortNGrams(bigrams)
  }
 
 
}