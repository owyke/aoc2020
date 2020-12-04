package aoc2020.day4.part2

import aoc2020.input.day

val input = day(4)
val mandatoryKeys = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")


fun main() {

    val passports = mutableListOf<Map<String, String>>()
    var currentPassport = mutableMapOf<String, String>()
    input.forEach { line ->
        if (line.isEmpty()) {
            passports.add(currentPassport)
            currentPassport = mutableMapOf()
        } else {
            line.split(" ")
                .flatMap { tokens -> tokens.split(" ") }
                .map { token -> token.split(":") }
                .forEach { entry -> currentPassport.put(entry[0], entry[1]) }
        }
    }
    passports.add(currentPassport)

    val res = passports
        .filter { pp -> (mandatoryKeys - pp.keys).isEmpty() }
        .filter {
            it.all { (k, v) ->
                when (k) {
                    "byr" -> v.intBetween(1920, 2002)
                    "iyr" -> v.intBetween(2010, 2020)
                    "eyr" -> v.intBetween(2020, 2030)
                    "hgt" -> if (v.endsWith("cm")) {
                        v.substring(0, 3).intBetween(150, 193)
                    } else if (v.endsWith("in")) {
                        v.substring(0, 2).intBetween(59, 76)

                    } else {
                        false
                    }
                    "hcl" -> v.matches("#[0-9a-f]{6}".toRegex())
                    "ecl" -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(v)
                    "pid" -> v.matches("[0-9]{9}".toRegex())
                    "cid" -> true

                    else -> error("unknown")
                }
            }
        }.count()
    println(res)
}

private fun String.intBetween(lo: Int, hi: Int): Boolean = this.toIntOrNull()?.let { it in lo..hi } ?: false


