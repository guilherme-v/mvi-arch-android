package br.curitiba.android.mviarch.util

import java.util.*
import java.util.concurrent.ThreadLocalRandom


fun String.Companion.random(): String {
    return UUID.randomUUID().toString()
}

fun Int.Companion.random(): Int {
    return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
}

fun Long.Companion.random(): Long {
    return Int.random().toLong()
}

fun Boolean.Companion.random(): Boolean {
    return Math.random() < 0.5
}
