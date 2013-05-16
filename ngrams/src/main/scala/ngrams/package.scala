import scala.io.Source
package object ngrams {
 
  def theChimesBook = loadBook("http://www.textfiles.com/etext/AUTHORS/DICKENS/dickens-chimes-379.txt")
 
  def theChristmasBook = loadBook("http://www.textfiles.com/etext/AUTHORS/DICKENS/dickens-christmas-125.txt")
 
  def theTaleOfTwoCities = loadBook("http://www.textfiles.com/etext/AUTHORS/DICKENS/dickens-tale-126.txt")
 
  private def loadBook(url: String): Iterator[String] =
    Source.fromURL(url).getLines
}