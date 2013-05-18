package soundex

object Soundex {
  val cutOff = 3
  val exclusions = Set('a', 'e', 'i', 'o', 'u', 'y', 'h', 'w')

  val codes = Map(
    '1' -> "bfpv", '2' -> "cgjkqsxz", '3' -> "dt",
    '4' -> "l", '5' -> "mn", '6' -> "r")

  val charCode: Map[Char, Char] =
    for ((digit, chars) <- codes; char <- chars) yield (char -> digit)

  def encode(word: String): String =
    word.toLowerCase.toSeq match {
      case firstLetter +: rest => {
        firstLetter +: rest.filterNot(exclusions)
				           .map(charCode)
				           .foldRight[List[Char]](Nil)((x, acc) =>
				             if (!acc.isEmpty && x == acc.head) acc else (x +: acc)).mkString
				           .padTo(cutOff, '0')
				           .take(cutOff)
      }   
      case _ => ""
    }
}
