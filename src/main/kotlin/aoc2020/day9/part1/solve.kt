package aoc2020.day9.part1

import aoc2020.input.day


val input = day(9)


fun main() {
    val res = input
        .map(String::toLong)
        .windowed(26,1,false)
        .first { nums ->
            val pre = nums.dropLast(1)
            pre.all{ t1 ->
                pre.all{ t2 ->
                    t1 + t2 != nums.last()
                }
            }
        }.last()

    println(res)
}
