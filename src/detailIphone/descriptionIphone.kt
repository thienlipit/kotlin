package detailIphone

import baseSavedPlantsPath
import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

data class DesciptionItemIphone(
    var paymentOffer: String,
    var bundledOffer: String,
    var specialOffer: String
)

fun main() {
    val desciptionListIphone: MutableList<DesciptionItemIphone> = mutableListOf()

    val doc: Document = Jsoup.connect("https://shopdunk.com/iphone-14-pro-max").get()

    val container = doc.getElementsByClass("woocommerce-product-details__short-description")
    println(container)

//    val elements: Elements? = container?.getElementsByTag("p")


//    println(elements)

    var count = 0
    if (container != null) {
        for (el in container) {
            println(el)
            val str1 = el.getElementsByTag("span").text()
            println("Print str1: $str1")
            val str2 = el.getElementsByTag("p").text()
//            val str3 = el.getElementsByTag("p")[1].text()
            println("Dong thu hai: $str2")
//            println("Dong thu ba: $str3")
//
//            if ( str1 != null ) {
////                val itemIphone = DesciptionItemIphone(imageUrl)
////                desciptionListIphone.add(itemIphone)
//                println(str1)
//                count ++
//            }
        }
    }
    println(count)


//Cach thu nhat
/*
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
 */

}
