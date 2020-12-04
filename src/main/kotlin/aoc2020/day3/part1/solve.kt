package aoc2020.day3.part1

import aoc2020.input.day



val input = day(3)
val width = input[0].length

fun main() {
    var posX = 0
    var posY = 0
    var trees = 0

    while(posY < input.size) {
        if(input[posY][posX] == '#') {
            trees++
        }
        posX = (posX + 3) % width
        posY = (posY + 1)
    }

    println(trees)
}
