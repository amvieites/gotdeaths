package getdeaths

import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

/**
 * Created by Alex on 18/06/2015.
 */
class PageExtractor {
  def extract(url: String): Seq[String] = {
    val browser = new Browser
    val doc = browser.get(url)
    val linksExtractor = elementList(".category-gallery-item") >> attr("href")("a")

    doc >> linksExtractor
  }
}
