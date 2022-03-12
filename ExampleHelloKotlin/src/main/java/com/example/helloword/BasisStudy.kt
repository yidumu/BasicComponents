package com.example.helloword

import androidx.constraintlayout.solver.widgets.Rectangle
import java.io.File
import java.lang.ArithmeticException
import java.lang.IllegalStateException

class BasisStudy {

    /**
     * Basic Syntax
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum_simple(a: Int, b: Int): Int = a + b

    /**
     * Unit 返回类型可省略
     */
    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }

    fun maxof(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun maxof_simple(a: Int, b: Int) = if (a > b) a else b;

    fun parseInt(str: String): Int? {
        return null
    }

    val items = listOf("apple", "banana", "kiwiftuit")
    fun printFtuit() {

        for (index in items.indices) {
            println("item at $index is ${items[index]} ")
        }

        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]} ")
            index++
        }
    }

    /**
     * when expression
     */
    fun describe(obj: Any): String =
        when (obj) {
            1 -> "one"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not is string"
            else -> "Unknown"
        }

    /**
     * Ranges
     */

    fun Ranges() {
        val x = 10
        val y = 9
        if (x in 1..y + 1) {
            println("first in range")
        }

        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.lastIndex) {

        }

        if (list.size !in list.indices) {

        }
        for (x in 1..5) {
            print(x)
        }
        for (x in 1..10 step 2) {

        }
        //半开
        for (i in 1 until 100) {

        }
        //downTo 倒叙
        for (x in 9 downTo 0 step 3) {

        }
        when {
            //检擦元素是否在集合里
            "apple" in items -> println("apple is fine too")
            "banana" in items -> println("banana is fine too")
        }
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }//过滤符合规则的集合
            .sortedBy { it }//排序
            .map { it.toUpperCase() }//转换成大写
            .forEach { println(it) }//对每个元素进行指定操作
        //矩形
        val rectangle = Rectangle()

    }

    data class Customer(val name: String, val email: String)

    fun foo(a: Int = 0, b: String = "") {
        var map = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
        for ((k, v) in map) {
            println("$k -> $v")
        }
    }

    /**
     * Lazy property
     */
    val p: String by lazy {
        "hello"
    }

    //Create a singleton
    object Resource {
        val name = "Name"
    }

    fun getFileList() {
        val files = File("Test").listFiles()
        println(files?.size)
        println(files?.size ?: "empty")

        val values = mapOf("name" to "tom", "email" to "@qq.com")
        val email = values["email"] ?: throw IllegalStateException("Email is miss")
        val mainEmail = email.firstOrNull() ?: ""
        mainEmail?.let { }
        try {

        } catch (e: ArithmeticException) {
            throw IllegalStateException(e)
        }
    }

    fun foo(param: Int) {
        val result = if (param == 1) {
            "one"
        } else {
            "three"
        }
        //交换2个值
        var a = 1
        var b = 2
        a = b.also { b = a }
    }

    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    fun foo() {
        val b: Boolean? = null
        if (b == true) {
        } else {
            // `b` is false or null
        }

        val myRectangle = Rectangle().apply {
            width = 4
            height = 5
        }
    }

    class Turtle {
        fun penDown() {}
        fun penUp() {}
        fun turn(degrees: Double) {}
        fun forward(pixels: Double) {}
    }

    val myTurtle = Turtle()

    fun methods() {
        with(myTurtle) {
            //draw a 100 pix square
            penDown()
            for (i in 1..4) {
                forward(100.0)
                turn(90.0)
            }
            penUp()
        }
    }

    fun generateAnswerString(countThread: Int): String {
        val length = stringMapper("Android") { input ->
            input.length //如果匿名函数是在某个函数上定义的
            // 最后一个参数，则您可以在用于调用该函数的圆括号
            // 之外传递它
        }
        return "this a answer $countThread $length"
    }

    val answer = generateAnswerString(12)

    val stringLengthFunc: (String) -> Int = { input ->
        input.length
    }

    val stringLength = stringLengthFunc("Android")

    fun stringMapper(str: String, mapper: (String) -> Int): Int {
        return mapper(str)
    }

}