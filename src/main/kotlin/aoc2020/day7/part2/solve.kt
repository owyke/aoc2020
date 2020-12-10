package aoc2020.day7.part2

import aoc2020.input.day


val input = day(7)


fun main() {
    val start = System.currentTimeMillis()
    val bagMap = (input.map { extractFull(it) }.toMap())
    println(System.currentTimeMillis() - start)
    println(sumBags(Color("shiny gold"), bagMap))
}

fun sumBags(color: Color, bags: Map<Color, Map<Color, Int>>): Int {
    val contents = bags.getValue(color)
    return when {
        contents.isEmpty() -> 0
        else -> contents.map {
                (color, number) -> number + number * sumBags(color, bags)
        }.sum()
    }
}

fun extractFull(full: String): Pair<Color, Map<Color, Int>> {
    val all = full.split(" bags contain ")
    return Color(all[0]) to extractContents(all[1])
}

fun extractContents(contents: String): Map<Color, Int> {
    return contents.split(" bags?, ".toRegex()).flatMap {
        if (it == "no other bags.") {
            emptyList()
        } else {
            it.removeSuffix(" bags.")
                .removeSuffix(" bag.")
                .split(", ")
                .map { numCol ->
                    require(numCol[0].isDigit()) { "[$numCol]" }
                    Color(numCol.substring(2)) to Character.getNumericValue(numCol[0])
                }
        }
    }.toMap()
}

inline class Color(val str: String)

