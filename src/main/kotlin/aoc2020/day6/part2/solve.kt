package aoc2020.day6.part2

import aoc2020.input.day


val input = day(6)


fun main() {
    val res = input.joinToString(separator = ":")
        .split("::")
        .map { group ->
            group.split(":")
                .map(String::toSet)
                .reduce { acc, e -> e.intersect(acc) }.size
        }.sum()

    println(res)
}