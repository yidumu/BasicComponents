import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val items = listOf(1, 2, 3, 4, 5)
    println(items.fold(1, { acc, i -> acc + i }))
    println(items.fold(1, Int::plus))
    println(items.fold("Elements:", { acc, i -> acc + i }))
    items.filter { it > 0 }
    var a = A()
    var c = CocoMode()
    println(c.p)
}

open class A {
    open fun foo(i: Int = 10, qux: (Int) -> Unit) {
    }

    open fun <T> cooc(acc: T, qux: (i: T, j: T) -> T) {
        println("cooc")
    }

    fun double(x: Int): Int = 2 * x

    fun powerOf(number: Int = 0, exponent: Int): Int {
        return 0
    }
}

//委托
interface IBase {
    fun print()
}

class CocoImpl : IBase {
    override fun print() {
        TODO("Not yet implemented")
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}


class CocoMode() : IBase by CocoImpl() {
    //属性委托
    var p: String by CocoImpl()

    val lazyValue: String by lazy(LazyThreadSafetyMode.NONE) { "a" }
}