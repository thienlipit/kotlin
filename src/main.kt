import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

data class Company(
    var name: String,
    var employees: Int,
    var offices: List<String>
)

data class ItemIphone(
    var name: String,
    var price: String,
    var urlImage: String,
    var urlDetail: String
)

data class User (
    val id: Int,
    val username: String,
    val password: String,
    val fullName: String
)

const val baseSavedPlantsPath = "./resources/data"
fun main() {
    val listCompany: MutableList<Company> = mutableListOf()
    val companies = Company(
        "Microsoft", 182268, listOf("California", "Washington", "Virginia")
    )
    val companies2 = Company(
        "LG", 1000, listOf("Korea", "Tokyo")
    )
    listCompany.add(companies)
    listCompany.add(companies2)


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
        if (title != null && imageUrl != null && price != null && urlItem != null) {
            val itemIphone = ItemIphone(title, price, imageUrl, urlItem)
            listIphone.add(itemIphone)
            println("$title --- $price --- $imageUrl --- $urlItem")
            count ++
        }

    }
    println(count)


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