package aoc2020.day4.part1

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

    println(passports.count { pp ->
        if((mandatoryKeys - pp.keys).isNotEmpty()) {println(pp)}
        (mandatoryKeys - pp.keys).isEmpty()
    }
    )
}