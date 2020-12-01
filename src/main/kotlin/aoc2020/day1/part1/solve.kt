package aoc2020.day1.part1

import aoc2020.input.day

val input = day(1)
    .map{Integer.parseInt(it)}
    .toSet()

fun main() {
    println(input.flatMap { outer ->
        input.filter { inner -> outer + inner == 2020  }
            .map{it * outer}
    }.distinct())
}