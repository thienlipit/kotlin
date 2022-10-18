package detailIphone

import baseSavedPlantsPath
import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

fun main() {
    val thongSoKyThuat: MutableList<String> = mutableListOf()
    val doc: Document = Jsoup.connect("https://shopdunk.com/iphone-14-pro-max").get()
    val container = doc.getElementsByClass("woocommerce-product-attributes shop_attributes")
    print(container)
    println("----------------------------------------------------------------------------")
    var count = 0
    for (ele in container){

        val dungLuong = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_dung-luong").text()
        val manHinh = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_man-hinh").text()
        val doPhanGiai = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_do-phan-giai-man-hinh").text()
        val cameraSau = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_camera-sau").text()
        val cameraTruoc = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_camera-truoc").text()
        val pin = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_pin").text()
        val sac = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_sac").text()
        val ketNoiMang = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_ket-noi-mang").text()
        val chip = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_chip").text()
        val ram = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_ram").text()
        val baoMat = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_bao-mat").text()
        val chongNuoc = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_chong-nuoc").text()
        val chatLieu = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_chat-lieu").text()
        val thuongHieu = ele.getElementsByClass("woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_pa_thuong-hieu").text()
        if (!dungLuong.isNullOrEmpty()){
            println("\n$dungLuong")
            println("\n$manHinh")
            println("\n$doPhanGiai")
            println("\n$cameraSau")
            println("\n$cameraTruoc")
            println("\n$pin")
            println("\n$ketNoiMang")
            println("\n$chip")
            println("\n$ram")
            println("\n$baoMat")
            println("\n$chongNuoc")
            println("\n$chatLieu")
            println("\n$thuongHieu")
        }
        thongSoKyThuat.add(dungLuong + "\n" + manHinh + "\n" + doPhanGiai + "\n" + cameraSau +
                "\n" + cameraTruoc + "\n" + pin + "\n" + ketNoiMang + "\n" + chip + "\n" + ram + "\n" + baoMat+ "\n" + chongNuoc + "\n" + chatLieu + "\n" + thuongHieu)
        thongSoKyThuat.add("testdata")
        count++
    }
    print(count)

//Cach thu nhat
///*
    try {
        val folder = File(baseSavedPlantsPath)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        PrintWriter(FileWriter("$baseSavedPlantsPath/ThongSoKyThuat.json")).use {
            val gson = Gson()
            val jsonString = gson.toJson(thongSoKyThuat)
            it.write(jsonString)
        }
        println("Save list phone to file")
    } catch (e: Exception) {
        e.printStackTrace()
    }
// */
};
