package com.oliver.adventofcode

import java.io.File

const val YEAR_2018 = "2018"
const val YEAR_2023 = "2023"

fun readFile(day: Int, year: String): List<String> {
    val uri = ClassLoader.getSystemClassLoader().getResource("$year/Day$day.txt")!!.toURI()
    return File(uri).useLines { it.toList() }
}