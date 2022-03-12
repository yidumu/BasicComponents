fun main(args: Array<String>) {
    //print(User("binging", 18).toString())
    //val b = BaseImpl(10)
    //DerivedDelegation(b).main()
    //copy("kotlin", "java", {})
    var computer = Computer()
    //print(bc.sum(1, 2))
    computer.delaySum()
}

//fun
interface IntPredicate {
    fun accept(i: Int): Boolean
}

//可见性
//在 Kotlin 中有这四个可见性修饰符：private、 protected、 internal 和 public。
//如果没有显式指定修饰符的话，默认可见性是 public
//如果你不指定任何可见性修饰符，默认为 public，这意味着你的声明将随处可见；
//如果你声明为 private，它只会在声明它的文件内可见；
//如果你声明为 internal，它会在相同模块内随处可见；
//protected 不适用于顶层声明。
//局部变量、函数和类不能有可见性修饰符。

//拓展
//伴生对象
class MyClass {
    companion object {}  // 将被称为 "Companion"
}

//密封类 sealed 修饰符
//使用密封类的关键好处在于使用 when 表达式 的时候，如果能够验证语句覆盖了所有情况，就不需要为
//该语句再添加一个 else 子句了
sealed class Expr


data class User(val name: String, val age: Int)

class Box<T>(t: T) {
    var value = t
}

interface Sourec<out T> {
    fun next(): T
}

fun SourecString(start: Sourec<String>) {
    val end: Sourec<Any> = start
}

fun copy(from: String = "kotlin", to: String, qux: () -> Unit) {
    print("$from -> $to ")
}

//嵌套类和内部类
//标记为 inner 的嵌套类能够访问其外部类的成员

//枚举类
enum class Directiom {
    NORTH, SOUTH, WEST, EAST
}

//内联 inline
object DataProviderManager {

}

typealias NodeSet = Set<String>

interface BaseDelegation {
    fun print()
}

class BaseImpl(private val x: Int) : BaseDelegation {
    override fun print() {
        println(x)
    }
}

class DerivedDelegation(b: BaseDelegation) {
    private val base: BaseDelegation = b
    fun main() {
        base.print()
    }

    val lazyValue: String by lazy {
        "hello"
    }
}

//标准委托

//中缀表达式
//标有 infix 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用。中缀函数必须满足以下要求：
//它们必须是成员函数或扩展函数；
//它们必须只有一个参数；
//其参数不得接受可变数量的参数且不能有默认值。
class Computer {
    infix fun shl(x: Int) {

    }

    private val items = listOf(1, 2, 3)

    fun delaySum() {
        print(items.fold(0) { acc, i ->
            acc + i
        })
    }

    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

    var sumUser = { }

    val onClick: () -> Unit = {}
}