package soundex

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class SoundexSuite extends FunSuite {
  
  import soundex._

  trait TestWords {
    val Robert = "Robert"
    val Rupert = "Rupert"
    val Rubin  = "Rubin"
    val Ashcraft = "Ashcraft"
    val Ashcroft = "Ashcroft"
    val empty = ""
  }

  test("empty string tests") {
    new TestWords {
      assert(Soundex.encode(empty) === "")
    }
  }
  
  test("wikipedia samples") {
    new TestWords {
      assert(Soundex.encode(Robert) === "r163")
      assert(Soundex.encode(Robert) === Soundex.encode(Rupert))
      assert(Soundex.encode(Ashcraft) === Soundex.encode(Ashcroft))
      assert(Soundex.encode(Rubin) === "r150")
    }
  }
}
