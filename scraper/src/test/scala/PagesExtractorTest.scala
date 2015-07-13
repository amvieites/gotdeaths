import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
 * Created by Alex on 18/06/2015.
 */
class PagesExtractorTest extends FunSuite with BeforeAndAfterEach {

  override def beforeEach() {

  }

  test("testExtract") {
    val url = "http://gameofthrones.wikia.com/wiki/Category:Status:_Dead?title=Category%3AStatus%3A_Dead&page=1"
    val expectedPages = 25

    val extractor = new PagesExtractor
    val extract = extractor.extract(url)

    assert(expectedPages == extract, "Extract pages")
  }

}
