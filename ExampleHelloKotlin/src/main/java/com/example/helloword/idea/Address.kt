import javax.security.auth.Subject

class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"

    /* 属性的完整声明
    var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]*/
    private val simple: Int? = 1
    val isEmpty: Boolean get() = this.simple == 0

    var stringRepresentation: String = ""
        get() = this.toString()
        set(value) {
            setDataFromString(value) // 解析字符串并赋值给其他属性
            field = value
        }
    var counter: Int = 0 // 注意：这个初始器直接为幕后字段赋值
        set(value) {
            print("input value is $value field is $field \n")
            if (value > 10) field = value
        }

    private fun setDataFromString(value: String) {
        print(value)
    }

    lateinit var subject: String
    //const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

    fun setup() {
        if (true) { //要检测一个 lateinit var 是否已经初始化过，请在该属性的引用上使用 .isInitialized
            subject = "late init"
        } else {
            println("late init")
        }
    }
}