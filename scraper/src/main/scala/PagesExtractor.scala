import net.ruippeixotog.scalascraper.browser.Browser
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
/**
 * Created by Alex on 18/06/2015.
 */
class PagesExtractor {
  def extract(url: String): Int = {
    val browser = new Browser
    val doc = browser.get(url)
    val lksf: Elements = doc >> elements(".wikia-paginator li")
    asInt(lksf.get(6).text())
  }
}
