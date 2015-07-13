import net.ruippeixotog.scalascraper.browser.Browser

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.util.Validated._
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import scala.collection.mutable
import scala.collection.mutable.Map
import scala.util.control.Breaks._

/**
 * Created by Alex on 18/06/2015.
 */
class CharacterExtractor {

  def extractFields(infotable: List[Element], map: Map[String, String]): Map[String, String] = infotable match {
    case h :: t => {
      if (h.children.size() == 2) {
        extractFields(t, map + (h.children.get(0).text() -> h.children.get(1).text().replace("\"", "")))
      } else {
        extractFields(t, map)
      }
    }
    case _ => map
  }

  def extract(url: String, fields: Array[String]): String = {
    val browser = new Browser
    val doc = browser.get(url)
    val infotable: List[Element] = elementList (doc >> elements(".infobox tbody tr"))

    if (infotable != null && infotable.nonEmpty) {
      val map: Map[String, String] =
        extractFields(infotable, Map("name" -> infotable.head.child(0).text()))

      (filtert(fields, map) values) mkString ";"
    } else {
      ""
    }
  }

  def filtert(fields: Seq[String], map: Map[String, String]): collection.Map[String, String] = {
    var newMap = mutable.Map[String, String]()
    breakable {
      for (f <- fields) {
        val value = ff(f) (map get f)
        if (value isEmpty) {
          println("Skipping " + (map get "name"))
          newMap clear()
          break()
        }
        newMap += (f -> value)
      }
    }
    newMap
  }

  def ff(f: String): Option[String] => String = f match {
    case "Appeared in" => x: Option[String] => if (x isDefined) x.get.split(" ").head else ""
    case _ => x: Option[String] => if (x isDefined) x get else ""
  }
}
