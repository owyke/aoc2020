package aoc2020.day10.part1

import aoc2020.input.day


val input = day(10)


fun main() {
    val diffs = input.map(String::toInt)
        .sorted()
        .reversed()
        .windowed(2)
        .fold(emptyMap<Int,Int>()) { acc, nums ->
            val diff = nums[0]-nums[1]
            val currentCount = acc.getOrDefault(diff,1)
            acc + mapOf(diff to currentCount + 1)

    }

    println(diffs.getValue(3) * diffs.getValue(1))
}
