package com.gvolpe.hll

import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import org.jsoup.nodes.Element

import scala.util.{Failure, Success, Try}

object WikipediaScraper {
  def apply(): WikipediaScraper = new WikipediaScraper()
}

class WikipediaScraper {

  val url = "https://en.wikipedia.org/wiki/List_of_progressive_rock_artists"
  val browser = new Browser().withHttpProxy("localhost", 3128)

  def progRockBands: Seq[String] = {
    val wiki = browser.get(url)

    val start: List[Element] = wiki >> elementList(".div-col")
    val items: List[Element] = (start >> elementList("li")).flatten

    val bands: Seq[String] = items map { e =>
      val band: Try[Element] = Try(e >> element("a"))
      band match {
        case Success(a) => a.text
        case Failure(t) => e.text
      }
    }

    bands.take(542)
  }

}