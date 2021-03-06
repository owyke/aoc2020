package aoc2020.day5.part2

import aoc2020.input.day

val input = day(5)


fun main() {
    val sortedSeats = input.map { getSeat(it) }.sorted()
    val res = (sortedSeats.first()..sortedSeats.last())
        .find { !sortedSeats.contains(it) }
    println(res)

}

fun getSeat(instruction: String): Int {
    var verticalDelta = 64
    var horizontalDelta = 4
    var front = 0
    var back = 127
    var left = 0
    var right = 7

    instruction.forEach {
        when (it) {
            'L' -> {
                right -= horizontalDelta
                horizontalDelta /= 2
            }
            'R' -> {
                left += horizontalDelta
                horizontalDelta /= 2
            }
            'F' -> {
                back -= verticalDelta
                verticalDelta /= 2
            }
            'B' -> {
                front += verticalDelta
                verticalDelta /= 2
            }

        }
    }
    require(front == back)
    require(left == right)
    return front * 8 + left
}
