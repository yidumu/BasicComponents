package com.example.helloword.example

import kotlinx.coroutines.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple(): List<Int> = listOf(1, 2, 3)
fun simpleSequence(): Sequence<Int> = sequence { // 序列构建器
    for (i in 1..3) {
        Thread.sleep(500L) // 假装我们正在计算
        yield(i) // 产生下一个值
    }
}

fun main() {
    simpleSequence().forEach { value ->
        print(value)
    }
}
