import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


data class Category(
    var src: String,
    var name: String,
    var totalRating: Float,
    var sale: String,
    var price: Int,
    var priceDiscount: Int,
    var badgeUnderPrice: String,
)

const val baseSavedPlantsPath = "./resources/data"
fun main() {
    val listMacbook: MutableList<Category> = mutableListOf()
    val doc: Document = Jsoup.connect("https://tiki.vn/macbook-imac/c2458").get()
//    println(doc)
    val elements = doc.getElementsByClass("ProductList__Wrapper-sc-1dl80l2-0 iPafhE").first()


//    println(elements)
    println(elements?.childrenSize())
    var i = 0

    for (i in 0 until elements!!.childrenSize()) {
        val src = elements.getElementsByTag("picture")
        val name = elements.getElementsByClass("name")[i]?.getElementsByTag("h3")?.text()
        println(src)
        println(name)
    }

//    for (element in scriptElements) {
//        for (node in element.dataNodes()) {
//            println(node.wholeData)
//        }
//        println("-------------------")
//    }


//    val elements: Elements? = container?.getElementsByClass("elementor-widget-wrap") ?: return



//Cach thu nhat

//    try {
//        val folder = File(baseSavedPlantsPath)
//        if (!folder.exists()) {
//            folder.mkdirs()
//        }
//
//        PrintWriter(FileWriter("$baseSavedPlantsPath/listPhone.json")).use {
//            val gson = Gson()
//            val jsonString = gson.toJson(listIphone)
//            it.write(jsonString)
//        }
//        println("Save list phone to file")
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }



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