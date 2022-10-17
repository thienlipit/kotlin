import com.google.gson.Gson
import detailIphone.DetailItemIphone
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

data class DetailListImageIphone(
    var url: String
)

data class ItemIphone(
    var id: Int,
    var name: String,
    var price: String,
    var urlImage: String,
    var urlDetail: String,
    var listImage: List<DetailListImageIphone>

)

data class User (
    val id: Int,
    val username: String,
    val password: String,
    val fullName: String
)

const val baseSavedPlantsPath = "./resources/data"
fun main() {


    val listIphone: MutableList<ItemIphone> = mutableListOf()

    val doc: Document = Jsoup.connect("https://shopdunk.com/iphone").get()


    val container = doc.getElementsByClass("jet-listing-grid jet-listing").first()


    val elements: Elements? = container?.getElementsByClass("elementor-widget-wrap") ?: return

//    println(elements)

    var count = 0
    for (el in elements!!) {
        val title = el.getElementsByClass("elementor-heading-title elementor-size-default").first()?.text()
//        val imageContainer =  el.getElementsByClass("elementor-image").first()?.getElementsByTag("img")?.attr("data-orig-file")
//        println(imageContainer)


        val imageUrl = el.getElementsByClass("elementor-image").first()?.getElementsByTag("img")?.attr("data-orig-file")
        val price = el.getElementsByClass("jet-listing-dynamic-field__content").first()?.text()
        val urlItem = el.getElementsByTag("a").first()?.attr("href")


        //-------------------------------------------------------------------------------
        val detailListIphone: MutableList<DetailListImageIphone> = mutableListOf()
        if ( !urlItem.isNullOrEmpty()) {
            println("url: $urlItem")
            var url = urlItem
            val docImage: Document = Jsoup.connect("$url").get()

            val containerImage = docImage.getElementsByClass("iconic-woothumbs-thumbnails__slide ")

            for (element in containerImage) {
                val imageUrl1 =
                    element.getElementsByClass("iconic-woothumbs-thumbnails__image-wrapper").first()?.getElementsByTag("img")
                        ?.attr("nitro-lazy-src")
                if (imageUrl1 != null) {
//                    val write = "{ 'url': '$imageUrl1' }"
                    val imageItemIphone = DetailListImageIphone(imageUrl1)
                    detailListIphone.add(imageItemIphone)

                }
            }

        }
        val distinct = detailListIphone.distinct().toList()

        //---------------------------------------------------------------------------------
        if (title != null && imageUrl != null && price != null && urlItem != null) {
            val itemIphone = ItemIphone(count, title, price, imageUrl, urlItem, distinct)
            listIphone.add(itemIphone)
            count ++
        }

    }
//    println(count)


//Cach thu nhat

    try {
        val folder = File(baseSavedPlantsPath)
        if (!folder.exists()) {
            folder.mkdirs()
        }

        PrintWriter(FileWriter("$baseSavedPlantsPath/listPhone.json")).use {
            val gson = Gson()
            val jsonString = gson.toJson(listIphone)
            it.write(jsonString)
        }
        println("Save list phone to file")
    } catch (e: Exception) {
        e.printStackTrace()
    }



    //Cach thu hai
    /*
    val mapper = jacksonObjectMapper()
    val userList = mutableListOf<User>()
    userList.add(User(102, "jsmith", "P@ss", "John Smith"))
    userList.add(User(103, "janed", "Pass1", "Jane Doe"))
    val jsonArray = mapper.writeValue(File("./resources/data/listPhone.json"),userList)
    println(jsonArray)

     */


}