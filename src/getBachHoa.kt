import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.FileWriter
import java.io.PrintWriter


data class ItemIphone1(
    var urlImage: String
)

fun main() {

    val listIphone: MutableList<ItemIphone1> = mutableListOf()

    val doc: Document = Jsoup.connect("https://www.dienmayxanh.com/dien-thoai-apple-iphone").get()
    val container = doc.getElementsByClass("listproduct").first()

    println(container?.toString())
    val elements: Elements? = container?.getElementsByTag("img") ?: return

    var count = 0
    for (el in elements!!) {
        val imageUrl = el.attr("src")

        if ( imageUrl != null ) {
            val itemIphone = ItemIphone1( imageUrl)
            listIphone.add(itemIphone)
            println("$imageUrl")
            count ++
        }

    }
    println(count)


//Cach thu nhat

    try {


        PrintWriter(FileWriter("$baseSavedPlantsPath/itemBHX.json")).use {
            val gson = Gson()
            val jsonString = gson.toJson(listIphone)
            it.write(jsonString)
        }
        println("Save list phone to file")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}