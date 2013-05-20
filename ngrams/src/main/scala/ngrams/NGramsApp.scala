package ngrams

object NGramsApp {

  val newLine: String = sys.props("line.separator")
  
  def main(args: Array[String]) {
    if (args.isEmpty) {
      println("Please supply a file path to a text corpus.")
      System.exit(0)
    } else args.map(arg => processFile(arg))
      
    System.exit(0)
  }
  
  def processFile(filepath: String): Unit = {
    val ngrams = new NGrams(readFile(filepath))	
    println(s"$newLine Results for $filepath: $newLine")
    
    println(s"$newLine Unigrams: $newLine")
    printUnigrams(ngrams.unigrams.take(50))
    
    println(s"$newLine Bigrams: $newLine")
    printBigrams(ngrams.bigrams.take(50))
  }
  
  def printUnigrams(results: Seq[Any]): Unit = 
    results.foreach{ case (word, probability) => 
    	println(s"Unigram '$word' occurs with probability: $probability")}
  
  def printBigrams(results: Seq[Any]): Unit = 
    results.foreach{ case ((word, history), probability) => 
    	println(s"Bigram ('$word'|'$history') occurs with probability: $probability")}
    
  def readFile(filepath: String): Iterator[String] = {
    try {
      val s = io.Source.fromFile(filepath)
      s.getLines
    } catch {
      case e: Exception =>
        println("Could not load corpus: " + e)
        throw e
    }
  }
}
