/**
 * 类可以包含
 * -构造函数与初始化模块
 * -函数
 * -属性
 * -嵌套类与内部类
 * -对象声明
 */
//如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字
class Invoice constructor(name: String) {
    val fistProperty = "First property: $name".also(::println)

    //主构造函数不能含有任何代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块
    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    //可变的（var）或只读的（val）
    val customerKey = name.toUpperCase()

}