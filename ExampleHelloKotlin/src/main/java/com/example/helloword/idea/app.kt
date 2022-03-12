fun main(args: Array<String>) {
    println("Hello World！")
    //printString(sum(1, 2).toString())
    //baseType()
    expression()
}

fun sun(a: Int, b: Int): Int {
    return a + b
}

public fun sum(a: Int, b: Int) = a + b
fun printString(string: String) {
    println(string)
}

//基本类型
fun baseType() {
    val one = 1_000_000
    val oneLong = 1L // Long
    val oneByte: Byte = 1
    val pi = 3.14 // Double
    val e = 2.7182818284f // Float
    val hexByte = 0xFF
    val bytes = 0b1111_0000
    val a: Int = 1000
    val b: Int = 0
    val boxedA: Int? = a
    val l = 1L + 3 // Long + Int => Long
    val x = 5 / 2
    val interval = x in a..b
    val au = 1UL // 无符号

    println(interval)
    printString(boxedA.toString())
    println(boxedA == a)

    val str = "ab\ncd"
    for (c in str) {
        print(c)
    }

    val text = """
        | Tell me and I forget
        | Teach me and I remember
    """.trimIndent()
    val i = 10
    print("i = $i ${text.length} is '$'text.length")

}

//表达式
fun expression() {
    val a = 1
    val b = 2
    var max = if (a > b) a else b
    //val input = readLine()

    val min = if (b > a) {
        a
    } else {
        b
    }

    val x = 10
    when (x) {
        in 1..10 -> print("x is in the range")
        !in 10..20 -> print("x is outside the range")
        0, 1, 10 -> print("x == 0 or x == 1")
        else -> print("none of the above")
    }

    print(hasPrefix("prefix"))

    when {
        x is Int -> print("x is Int")
        x is Int -> print("x is Int")
        else -> print("none of the above")
    }

    for (i in 1..3) {
        print(i)
    }

    for (i in 6 downTo 0 step 2) {
        print(i)
    }
    println("\n---foo---")
    foo()
    fooloop()

}

fun foo() {
    listOf<Int>(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return
        print(value)
    })
    print(" done with implicit label")
}

fun fooloop() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            print(it)
        }
    }
    print(" done with implicit label")
}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}
