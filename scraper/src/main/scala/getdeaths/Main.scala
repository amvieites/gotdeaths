package getdeaths

/**
 * Created by Alex on 17/06/2015.
 */
object Main {
  def main(args: Array[String]) {
    val url = "http://gameofthrones.wikia.com/wiki/Category:Status:_Dead?title=Category%3AStatus%3A_Dead&page="
    val lastPage = new PagesExtractor extract(url)
    val fields = Array[String]("name", "Allegiance", "First seen", "Last seen", "Appeared in")

    var str = ""
    for (i <- 1 to lastPage) {
      val urls = new PageExtractor extract(url + i)
      /*for (u <- urls) {
        str = str + (new CharacterExtractor extract(u,
                    Array[String]("name", "Allegiance", "First seen", "Last seen", "Appeared in"))) + "\n"
      }*/
      str = Array(str, urls map (u => new CharacterExtractor extract(u, fields)) filter(s => s nonEmpty) mkString "\n") mkString "\n"
    }

    println(str)
  }
}
