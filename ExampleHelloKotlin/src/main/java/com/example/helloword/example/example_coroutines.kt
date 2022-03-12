package com.example.helloword.example

import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }
    println("Hello,") // 协程已在等待时主线程还在继续
    //Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

    runBlocking {     // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    }

    val coroutineJob = GlobalScope.launch(Dispatchers.Main) { // 在后台启动一个新的协程并继续
        //返回值Job用于执行取消操作
    }
    //coroutineJob.join()
    //val deferredJob = async {}

    //挂起函数
    suspend fun doWork() {
        delay(1000)
    }

    //协程取消
    runBlocking {
        val job = launch {
            delay(500)
        }
        job.cancelAndJoin()
    }

    //超时处理
    fun timeOutCoroutines() = runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                delay(500L)
            }
        }
    }

    /**
     * 协程上下文（实际控制协程在那个线程执行）
     * launch和async都可接收CoroutineContext函数控制协程执行的线程
     * Dispatchers.Unconfined一种特殊的调度器（非受限调度器），运行在默认的调度者线程，挂起后恢复在默认的执行者kotlinx.coroutines.DefaultExecutor中执行
     * Dispatchers.Default 默认调度器，采用后台共享的线程池（不传上下文，默认采用这种）
     * newSingleThreadContext 单独生成一个线程
     * Dispatchers.IO IO线程
     */
    fun coroutineContext() = runBlocking {
        launch { // 运行在父协程的上下文中，即 runBlocking 主协程
        }
        launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
        }
        launch(Dispatchers.Default) { // 将会获取默认调度器
        }
        launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
        }
        launch(Dispatchers.IO) {
        }
    }
}

suspend fun test() {
}