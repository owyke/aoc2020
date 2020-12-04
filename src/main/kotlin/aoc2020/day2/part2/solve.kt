package aoc2020.day2.part2

import aoc2020.input.day

data class Input(
    val min: Int,
    val max: Int,
    val char: Char,
    val pass: String
)

val input = day(2)
    .map{"""(\d+)-(\d+) ([a-z]): ([a-z]+)""".toRegex().matchEntire(it)!!.destructured}
    .map{(min, max, char, pass) -> Input(min.toInt(),max.toInt(),char.toCharArray()[0],pass)}

fun main() {

    println(input.count { entry ->
        (entry.pass[entry.min - 1] == entry.char) xor (entry.pass[entry.max - 1] == entry.char)
    })
}