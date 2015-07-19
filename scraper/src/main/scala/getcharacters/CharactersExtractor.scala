package getcharacters

import org.jsoup.nodes.Element
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.dsl.DSL._
/**
 * Created by Alex on 16/07/2015.
 */
class CharactersExtractor {
  def extractCharacters(episode: Int, episodeData: Element): List[CharacterRow] = {
    val aux = episodeData >> extractor(".char", texts)
    aux.toList map (x => new CharacterRow(x, "Unknown", episode, 1)) filter (x => !x.name.isEmpty && !x.name.contains("credited"))
  }
}
