package aoc2020.day7.part1

import aoc2020.input.day


val input = day(7)


fun main() {
    val bagMap = (input.map{extractFull(it)}.toMap())
    val imploded = bagMap.keys.map{ it to implode(it, bagMap) }.toMap()
    println(imploded.count { (_,v) -> v.contains(Color("shiny gold")) })
}

fun implode(color: Color, bagMap: Map<Color, Map<Color, Int>>): Set<Color> {
    val contents = bagMap.getValue(color).keys.toSet()
    return contents + contents.flatMap{implode(it, bagMap)}
}



fun extractFull(full: String): Pair<Color, Map<Color,Int>> {
    val all = full.split(" bags contain ")
    return Color(all[0]) to extractContents(all[1])
}

fun extractContents(contents: String): Map<Color, Int> {
        return contents.split(" bags?, ".toRegex()).flatMap {
            if(it =="no other bags.") {
                emptyList()
            } else {
            it.removeSuffix(".")
                .removeSuffix(" bags")
                .removeSuffix(" bag")
                .split(", ")
                .map { numCol ->
                    require(numCol[0].isDigit()) {"[$numCol]"}
                    Color(numCol.substring(2)) to Character.getNumericValue(numCol[0])
                }
            }
        }.toMap()
    }

inline class Color(val str: String)

