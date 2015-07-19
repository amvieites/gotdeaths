package getcharacters

import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.dsl.DSL._
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
/**
 * Created by Alex on 15/07/2015.
 */
object Main {
  def main(args: Array[String]) {
    val url = "http://www.imdb.com/title/tt0944947/epcast"
    val browser = new Browser
    val doc = browser.get(url)

    val episodeNodes = doc >> elementList (".cast")

    def loop(episodes: List[Element], i: Int): List[CharacterRow] = episodes match {
      case h :: t => (new CharactersExtractor extractCharacters(i, episodeNodes(i-1))) ::: loop(t, i + 1)
      case Nil => List()
    }

    val rows = loop(episodeNodes, 1)
    //def transFun(v:String, l:List[CharacterRow]):Int = l.size
    val episodeCount = rows groupBy(_.name) transform((v, l) => l.size)

    //rows.foreach(cr => cr.episodes = episodeCount get cr.name)

    def printChar(c:CharacterRow):Any = print(c.name + "\t" + c.allegiance + "\t" + c.episode + "\t" + c.episodes + "\n")
    var rows2 = rows.map(cr => new CharacterRow(cr.name, cr.allegiance, cr.episode, episodeCount.get(cr.name).get))
    rows2.sortWith((cr1,cr2) => (cr1.episodes.compareTo(cr2.episodes) > 0)).foreach(printChar)
  }
}
