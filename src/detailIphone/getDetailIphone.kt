 package detailIphone

import baseSavedPlantsPath
import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

data class DetailItemIphone(
    var url: String
)

fun main() {
    val detailListIphone: MutableList<DetailItemIphone> = mutableListOf()
    val doc: Document = Jsoup.connect("https://shopdunk.com/iphone-14-pro").get()
    val container = doc.getElementsByClass("iconic-woothumbs-thumbnails__slide ")
    var count = 0
    for (el in container) {
        val imageUrl = el.getElementsByClass("iconic-woothumbs-thumbnails__image-wrapper").first()?.getElementsByTag("img")?.attr("nitro-lazy-src")
        if ( imageUrl != null ) {
            val itemIphone = DetailItemIphone(imageUrl)
            detailListIphone.add(itemIphone)
            println("$imageUrl")
            count ++
        }
    }
    println(count)

//Cach thu nhat
///*
    try {
        val folder = File(baseSavedPlantsPath)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        PrintWriter(FileWriter("$baseSavedPlantsPath/DetailListPhone.json")).use {
            val gson = Gson()
            val jsonString = gson.toJson(detailListIphone)
            it.write(jsonString)
        }
        println("Save list phone to file")
    } catch (e: Exception) {
        e.printStackTrace()
    }
// */
}
