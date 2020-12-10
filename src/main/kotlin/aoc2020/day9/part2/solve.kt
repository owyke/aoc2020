package aoc2020.day9.part2

import aoc2020.input.day
import kotlin.system.exitProcess

val input = day(9).map(String::toLong)


fun main() {
    val invalid = input
        .windowed(26, 1, false)
        .first { nums ->
            val pre = nums.dropLast(1)
            pre.all { t1 ->
                pre.all { t2 ->
                    t1 + t2 != nums.last()
                }
            }
        }.last()

    val a = (input.indices).map { low ->
        (input.indices).map { high ->
            if (low < high) {
                val slice = input.slice((low..high))
                if (slice.sum() == invalid) {
                    println(slice.min()!! + slice.max()!!)
                    exitProcess(0)
                }
            }
        }
    }
    println(a)

}