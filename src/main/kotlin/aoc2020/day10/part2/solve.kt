package aoc2020.day10.part2

import aoc2020.input.day

val input = day(10).map(String::toInt)


fun main() {
    val adapters = (input + (input.max()!! + 3) + 0)
        .sorted()
        .reversed()
    val validConfigurations = validConfigurations(adapters)
    println(validConfigurations)
}

val memoize = mutableMapOf<List<Int>, Long>()
fun validConfigurations(adapters: List<Int>): Long {
    val compactableIndex = adapters.windowed(3).withIndex().firstOrNull { (i, nums) -> nums[0] - nums[2] <= 3 }?.index

    return if (compactableIndex == null) {
        1L
    } else {
        memoize.getOrPut(
            key = adapters,
            defaultValue = {
                validConfigurations(
                    adapters.subList(compactableIndex + 1, adapters.size)

                ) + validConfigurations(
                    adapters.subList(0, compactableIndex + 1) + adapters.subList(
                        compactableIndex + 2,
                        adapters.size
                    )
                )
            }

        )
    }
}

