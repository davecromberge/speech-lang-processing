package soundex

object Application extends App {
  override def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println("Please provide a sequence of words as arguments.")
      System.exit(1)
    }
    System.exit(0)
  }
}