package aoc2020.day6.part1

import aoc2020.input.day


val input = day(6)


fun main() {
    val res = input.joinToString(separator = ":")
        .split("::")
        .map {
            it.split(":").joinToString("").toSet()
        }.sumBy { it.size }

    println(res)
}
