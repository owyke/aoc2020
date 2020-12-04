package aoc2020.day3.part2

import aoc2020.input.day

val input = day(3)
val width = input[0].length


val slopes = listOf(
    1 to 1,
    3 to 1,
    5 to 1,
    7 to 1,
    1 to 2

)
fun main() {
    val res = slopes.map {(movX, movY) ->
        var posX = 0
        var posY = 0
        var trees = 0L

        while(posY < input.size) {
            if(input[posY][posX] == '#') {
                trees++
            }
            posX = (posX + movX) % width
            posY = (posY + movY)
        }
        println(trees)
        trees
    }.reduce{acc,e -> acc * e}


    println(res)
}