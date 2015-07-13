import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
 * Created by Alex on 18/06/2015.
 */
class CharacterExtractorTest extends FunSuite with BeforeAndAfterEach {

  override def beforeEach() {

  }

  test("testExtract") {
    val charUrl = "http://gameofthrones.wikia.com/wiki/Eddard_Stark"
    val fields = Array[String]("name", "Allegiance", "Last seen", "Appeared in")
    val expectedData = "House Stark;Eddard Stark;Fire and Blood The Lion and the Rose (Bran's vision);11"
    val extractor = new CharacterExtractor
    assert(expectedData == extractor.extract(charUrl, fields), "Retrieve Eddard Stark")
  }

  test("testExtractAbsentFields") {
    val charUrl = "http://gameofthrones.wikia.com/wiki/Edgar_Yronwood"
    val fields = Array[String]("name", "Allegiance", "First seen", "Last seen", "Appeared in")
    val expectedData = ""
    val extractor = new CharacterExtractor
    assert(expectedData == extractor.extract(charUrl, fields), "Some fields not present -> discard")
  }

}
