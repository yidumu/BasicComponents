fun main(args: Array<String>) {
    println("Hello World！")
    val cinvoice = Invoice("Joe Smith");
}

//open 开放继承，默认final不能被继承
open class Person(val name: String) {
    var children: MutableList<Person> = mutableListOf()

    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}

class Customer(name: String) : Person(name) {

}

open class Base {
    open val vertexCount: Int = 0

    constructor(ctx: String) {

    }

    open fun fill() {
    }
}

class Derived : Base {
    //覆盖属性，可以用一个 var 属性覆盖一个 val 属性，但反之则不行
    override val vertexCount: Int = 1
    //get() = super.vertexCount

    constructor(ctx: String) : super(ctx)

    //覆盖方法
    override fun fill() {
        super.fill()
    }

}

//初始化是主构造函数的一部分，此构造函数第一条语句调用主构造函数
//如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数
class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

open class Rectangle {
    open fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor: String get() = "black"
}

class FilledRectangle : Rectangle() {
    fun fill() {

    }

    //内部类
    inner class Filler {
        fun drawAndFill() {
            fill()
        }
    }
}

open class Polygon {
    open fun draw() {}
}

abstract class PolygonImp : Polygon() {
    abstract override fun draw()
}