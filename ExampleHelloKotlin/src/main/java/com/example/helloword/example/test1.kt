package com.example.helloword.example

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch {
        delay(1000L)
        println("Hello")
        doWorld()
    }

    //只是挂起，会释放底层线程用于其他用途。
    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope")
        delay(2000L)
    }
    println("Coroutine scope is over")

    launch {
        repeat(100) { i ->
            println("sleep $i")
            delay(500L)
        }
    }
}

// 这是你的第一个挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}
