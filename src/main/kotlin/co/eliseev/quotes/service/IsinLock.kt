package co.eliseev.quotes.service

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock

object IsinLock {
    val locks = ConcurrentHashMap<String, ReentrantLock>()
}
