package soundex

object Application {

  val newLine: String = sys.props("line.separator")
  
  def main(args: Array[String]) {
    /*if (args.isEmpty) {
      println("Please supply a file path to a list of surnames.")
      System.exit(0)
    }*/
    
    val results =
      readFile("/home/dave/Development/speech-language-processing/soundex/surnames.txt").toSeq
      					 .map(line => line.split(" ")
       					 				  .map(surname => (surname, Soundex.encode(surname))))
       					 				
    printResults(results)
       					 				  					 				  
    System.exit(0)
  }
  
  def printResults(results: Seq[Array[(String, String)]]): Unit = 
    results.foreach(line => {
    	println(s"$newLine Soundex codes for " + line.map(_._1).mkString(", ") + s":$newLine")
    	line.foreach { case (surname, code) => println(s"$surname -> $code")}
    	if (line.map(_._2).distinct.length == 1) println("All are similar") else println("Not all similar")
    })
    
  def readFile(filepath: String): Iterator[String] = {
    try {
      val s = io.Source.fromFile(filepath)
      s.getLines
    } catch {
      case e: Exception =>
        println("Could not load surnames list: " + e)
        throw e
    }
  }
}
