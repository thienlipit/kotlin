package letrunalsoapplywith

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun main() {
    val demoCase = demoCase()
//    demoCase.demoLet()
//    demoCase.demoRun()
//    demoCase.demoLet_Run()
//    demoCase.demoAlso()
//    demoCase.demoLet_Also()
//    demoCase.demo_Apply()
    demoCase.demo_With()
}
data class Person(var name: String, var tutorial: String)
class demoCase{
    fun demo_With() {
        var person = Person("Anupam", "Kotlin")

        with(person)
        {
            name = "No Name"
            tutorial = "Kotlin tutorials"
        }
        println(person)
    }
    fun demo_Apply() {
        var person = Person("Anupam", "Kotlin")

        person.apply {
            tutorial = "Swift"
        }
        println(person)

        person.also {
            it.tutorial = "Kotlin"
        }
        println(person)
    }

    fun demoLet_Also(){
        var person = Person("Harry", "Kotlin")
        println(person)

        var l = person.let {
            it.tutorial = "Android"
            println(person)
        }
        var al = person.also {
            it.name = "tran"
            it.tutorial = "Java"

        }

        var r = person.run {
            name = "demo11"

        }

        println("let: $l")
        println("also: $al")
        println("run: $r")
        println(person)
    }
    fun demoAlso() {
        var m = 1
        m = m.also {
            val value1 = it + 1
            println("first = $value1")
        }.also {
            val value2 = it + 5
            println("second = $value2")
        }
        println(m)
    }

    fun demoLet_Run(){
        var p: String? = null
        println(p)
        p?.let {
            println("p is $p")
        } ?: run {
            println("p was null. Setting default value to: ")
            p = "Kotlin"
        }
        println(p)
    }

    fun demoRun() {
        var tutorial = "This is Kotlin tutorial"
        println(tutorial)
        tutorial = tutorial.run {
            val it = "This is run function"
            it
        }
        println(tutorial)
    }

    fun demoLet() {
        //Demo for let in kotlin
        var str = "abc"
        println(str)
        str.let {
            println("Print in let: $it")
            println("$it xyz")
        }
        println(str)

        var a = 1
        var b = 2
        a = a.let {
            it + 2
        }.let {
            val i = it + b
            i
        }
        println(a)
    }
}