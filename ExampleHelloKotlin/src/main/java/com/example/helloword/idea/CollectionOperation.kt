import java.util.*

fun main(args: Array<String>) {
    /*   //List
       val numbers = mutableListOf("one", "two", "three", "four")
       numbers.add("five")
   //    println("Number of elements: ${numbers.size}")
   //    println("Third element: ${numbers.get(2)}")
   //    println("Fourth element: ${numbers[3]}")
   //    println("Index of element \"two\" ${numbers.indexOf("two")}")

       val double = List(3, { it * 2 })
       val linkedList = LinkedList(listOf("one", "two", "three"))
   //    println(double)
   //    println(linkedList)
       val copyLinkedList = linkedList.toMutableList()
       val copySet = linkedList.toMutableSet()

       val longerThan3 = numbers.filter { it.length > 3 } //过滤
   //    println(numbers.associateWith { it.length }.values) //元素长度

       val numbersIterator = numbers.iterator()
       val numbersListIterator = numbers.listIterator()
       while (numbersIterator.hasNext()) {
           print("${numbersIterator.next()} ")
       }

       for (item in numbers) {
           print("$item ")
       }

       numbers.forEach { print("$it ") }
       while (numbersListIterator.hasNext()) {
           numbersListIterator.next()
       }

       while (numbersListIterator.hasPrevious()) {
           print("${numbersListIterator.previousIndex()}:${numbersListIterator.previous()}")
       }

       val mutableNumbers = numbers.toMutableList()
       val mutableIterator = mutableNumbers.iterator()
       mutableIterator.next()
       mutableIterator.remove()
       print("After remove : $mutableNumbers ")
       //Set
        val numbers = setOf(1, 2, 3, 4)  // LinkedHashSet is the default implementation
        val numbersBackwards = setOf(4, 3, 2, 1)
        println(numbers.first() == numbersBackwards.first())
        println(numbers.first() == numbersBackwards.last())

       val numbers = setOf(1, 2, 3)
       println(numbers.map { it * 3 })
       println(numbers.mapIndexed { idx, value -> value * idx })

       //Map
       val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
       println("All keys: ${numbersMap.keys}")
       println("All values: ${numbersMap.values}")
       if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
       if (1 in numbersMap.values) println("The value 1 is in the map")
       if (numbersMap.containsValue(1)) println("The value 1 is in the map")

       //区间
       val i = 0
       if (i in 1..4) {
           println(i)
       }
       for (i in 1..8 step 1) {
           print("$i ")
       }
       val versionRange = 1..30
       println(10 in versionRange)
       println((1..10).filter { it > 0 })

       //序列
       val numbersSequence = sequenceOf("four", "three", "two", "one")
       val numbersSequence1 = listOf("one", "two", "three", "four").asSequence()

       val oddNumbers = generateSequence(0) { it + 1 } // `it` 是上一个元素
       println(oddNumbers.take(10).toList())
       //println(oddNumbers.count())     // 错误：此序列是无限的。
       val oddNumbers1 = sequence {
           yield(1)
           yieldAll(listOf(3, 5))
           yieldAll(generateSequence(7) { it + 2 })
       }

       println(oddNumbers1.take(10).toList())

       val words = "The quick brown fox jumps over the lazy dog".split(" ")
       val lengthsList = words.map { it }.take(9)
       //println(lengthsList.toList())

       val mutableWord = mutableListOf<String>()
       lengthsList.filterTo(mutableWord) { it.length <= 3 }
       //println(mutableWord)
       lengthsList.filterIndexedTo(mutableWord) { index, s -> index < 3 }
       println(mutableWord)
       val sortedList = lengthsList.sorted()
       println(sortedList)

       val colors = listOf("red", "brown", "grey")
       val animals = listOf("fox", "bear", "wolf")
       println(colors zip animals)

       val twoAnimals = listOf("fox", "bear")
       println(colors.zip(twoAnimals))

       val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
       println(numberPairs.unzip())

       //println(numberPairs.unzip().first.associateBy { it })

       //嵌套集合
       val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
       println(numberSets.flatten())

       val containers = listOf(
           listOf("one", "two", "three")
           , listOf("four", "five", "six")
           , listOf("seven", "eight")
       )
       val listString = StringBuffer()
       containers.flatMap { it.toList() }.joinTo(listString)
       println(listString)
       println(containers.flatten().joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))
       val numbersToString = (1..100).toList()
       println(numbersToString.joinToString(limit = 10, truncated = "<...>"))
       val numbersMix = listOf(null, 1, "two", 3.0, "four")
       println("All String elements in upper case:")
       numbersMix.filterIsInstance<String>().forEach { _ ->
           //print("${it.toUpperCase()} ")
           //print("${it.length}")
       }
       val (match, rest) = containers.flatten().partition { it.length > 3 }
       println("match:$match rest:$rest")*/

    //检测谓词
    /*如果至少有一个元素匹配给定谓词，那么 any() 返回 true。
      如果没有元素与给定谓词匹配，那么 none() 返回 true。
      如果所有元素都匹配给定谓词，那么 all() 返回 true。
      注意，在一个空集合上使用任何有效的谓词去调用 all() 都会返回 true。
      这种行为在逻辑上被称为 vacuous truth。
    */

//    println("${match.any { it.endsWith("e") }}")
//    println(containers.flatten().none { it.endsWith("a") })
//    println(containers.flatten().all { it.endsWith("e") })

    //加减操作、分组
    /*
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    val plusList = numbers + "five"
    val minusList = numbers - listOf("three", "four")
    println(plusList)
    println(minusList)
    println(numbers.groupBy { it.first().toUpperCase() })
    println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))
    println(numbers.slice(1..3))
    println(numbers.slice(setOf(4, 3, 2)))
    println(numbers.takeLast(3))
    println(numbers.dropLast(2))
    println(numbers.takeWhile { !it.startsWith('t') })
    println(numbers.chunked(4))
    println((1..10).toList().windowed(3, step = 3, partialWindows = true))

    //取单个元素
    println(
        "${numbers.elementAt(0)} ${numbers.last()} " +
                "${numbers.elementAtOrElse(10) { index -> "The value for index $index is undefined " }}" +
                "${numbers.random()} " +
                "${numbers.contains("four")} " +
                "${numbers.isEmpty()}"
    )

    //排序
    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    println(listOf("aaa", "bb", "c").sortedWith(lengthComparator))
    println(listOf("aaa", "bb", "c").sortedBy { it.length })
    val reversedNumbers = numbers.reversed()
    println(reversedNumbers)
    println(numbers.shuffled())
    println(numbers.maxWith(compareBy { it.length }))
    */

    //list相关操作
/*
    val numbers = mutableListOf(1, 2, 3, 4, 5, 6, 9, 8, 7, 9, 3, 2, 7, 8, 9, 8, 2)
    val sum = numbers.reduce { sum, element -> sum + element }
    println(sum)
    val sumDoubled = numbers.fold(0) { sum, element -> sum + element }
    println(sumDoubled)
    println(numbers.subList(0, 3))//取集合一部分
    println(numbers.indexOf(9))
    numbers.sort()
    println(numbers)
    //numbers.fill(3) //替换所有元素的值
    numbers[0] = 2
    println(numbers.asReversed())
    println(numbers)
    println(numbers.binarySearch(3, 0, numbers.size - 1)) //指定搜索区间

    //set相关操作
    println(setOf(1, 2, 3) union (setOf(4, 5))) //并集
    println(setOf(1, 2, 3) intersect (setOf(3, 4, 5))) //交集
    println(setOf(1, 2, 3, 4, 5, 6) subtract (setOf(3, 4, 5))) //差集

    val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap["one"])
    println(numbersMap.getOrDefault("four", 10))
    println(numbersMap["five"])               // null
    //numbersMap.getValue("six")      // exception!
    numbersMap += mapOf("four" to 4);
    numbersMap.put("five", 5)
    println(numbersMap.keys) //所有键的集合
    println(numbersMap.values) //所有值的集合
    println(numbersMap.filterKeys { it == "one" })

*/

}

