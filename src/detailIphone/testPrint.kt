package detailIphone

fun main(){
    var list: MutableList<String> = mutableListOf()
    val test = "abcd"
    val str = "{ 'url': '$test'}"

    list.add(str)
    list.add(str)

    println(str)

    println(list)
}