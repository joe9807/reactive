package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking { // Главный корутин-блокирующий контекст
    println("Main thread starts: ${Thread.currentThread().name}")

    val jobs = List(10) { i -> // Запускаем 10 корутин
        launch(Dispatchers.Default) { // Используем Dispatchers.Default для фоновых вычислений
            println("Coroutine $i starts on thread: ${Thread.currentThread().name}")
            delay(1000L * (i + 1)) // Имитируем работу разной длительности
            println("Coroutine $i ends on thread: ${Thread.currentThread().name}")
        }
    }
    delay(10000L);
    println("Main thread is waiting for all coroutines to complete...")
    jobs.joinAll() // Ждем завершения всех корутин

    println("Main thread ends: ${Thread.currentThread().name}")
}