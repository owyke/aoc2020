package aoc2020.day1.part2

import aoc2020.input.day

val input = day(1)
    .map{Integer.parseInt(it)}
    .toSet()

fun main() {
    println(input.flatMap { outer ->
        input.flatMap { middle ->
            input.filter { inner -> outer +middle+ inner == 2020 }
                .map { it * middle * outer }
        }
    }.distinct())
}