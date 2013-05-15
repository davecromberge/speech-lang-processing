package soundex

object Application {

  def main(args: Array[String]) {
    if (args.isEmpty) {
      println("Please supply two words as arguments.")
      System.exit(0)
    } 
    val codes = args.map(x => Soundex.encode(x))
    args.zip(codes).map { case (word, code) => println(s"The soundex code for $word is $code") }
    
    if (codes.distinct.length == 1)
      println("All words are similar.")
    else
      println("Words are not similar.")
    
    System.exit(0)
  }
}
