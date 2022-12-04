package com.oliver.adventofcode

import java.io.File

fun readFile(day: Int): List<String> {
    val uri = ClassLoader.getSystemClassLoader().getResource("2018/Day$day.txt")!!.toURI()
    return File(uri).useLines { it.toList() }
}