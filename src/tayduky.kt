import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun main() {
    val doc: Document = Jsoup.connect("https://radiotruyen.info/truyen-kinh-dien/tay-du-ky-audio.html").get()
//    println(doc)

    val container = doc.getElementsByAttribute("src")
    println(container)
}