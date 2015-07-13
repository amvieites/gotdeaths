import net.ruippeixotog.scalascraper.browser.Browser
import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
 * Created by Alex on 18/06/2015.
 */
class PageExtractorTest extends FunSuite with BeforeAndAfterEach {

  override def beforeEach() {

  }

  test("testExtract") {
    val url = "http://gameofthrones.wikia.com/wiki/Category:Status:_Dead?title=Category%3AStatus%3A_Dead&page=1"
    val expectedItems = 16
    val extractor = new PageExtractor

    assert(expectedItems == extractor.extract(url).size)
  }

}
