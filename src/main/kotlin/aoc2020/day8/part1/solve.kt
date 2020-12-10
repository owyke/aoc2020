package aoc2020.day8.part1

import aoc2020.input.day


val input = day(8)


fun main() {
    val program = input.map (::parseInstruction)
    val initialState = State()
    println(runProgram(program, initialState))


}

fun runProgram(p: List<Instruction>, s: State, seen:List<Int> = emptyList()): Int {
    return if (s.pointer in seen) {
        0
    } else {
        when(val ins = p[s.pointer]) {
            is Nop -> runProgram(p, s.copy(pointer = s.pointer+1), seen + s.pointer)
            is Acc -> ins.parameter + runProgram(p, s.copy(pointer = s.pointer+1), seen + s.pointer)
            is Jmp -> runProgram(p, s.copy(pointer = s.pointer + ins.parameter), seen + s.pointer)
        }
    }
}


data class State(
    val pointer: Int = 0,
    val accumulator: Int = 0
)

sealed class Instruction(
    val parameter: Int
) {
    override fun toString(): String {
        return "${this::class.java.simpleName}(parameter=$parameter)"
    }
}
class Nop(
    parameter: Int
) : Instruction(parameter)

class Acc(
    parameter: Int
) : Instruction(parameter)

class Jmp(
    parameter: Int
) : Instruction(parameter)

fun parseInstruction(instruction: String): Instruction {
    val parts = instruction.split(" ")
    return when(parts[0]) {
        "nop" -> Nop(parts[1].toInt())
        "acc" -> Acc(parts[1].toInt())
        "jmp" -> Jmp(parts[1].toInt())
        else -> error("invalid instruction $instruction")


    }
}
