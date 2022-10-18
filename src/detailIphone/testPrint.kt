package detailIphone

fun main(){
    var testList: List<String> = listOf("one", "two", "one")
    println(testList)
    val distinct = testList.toSet().toList()
    println(distinct)

    var list: MutableList<String> = mutableListOf()
    val test = "abcd"
    val str = "{ 'url': '$test'}"

    list.add(str)
    list.add(str)

    println(str)

    println(list)
}