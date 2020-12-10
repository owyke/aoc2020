package aoc2020.day8.part2

import aoc2020.input.day




val input = day(8)


fun main() {
    val program = input.map (::parseInstruction)

    val jmpIndices = program.filterIsInstance(Jmp::class.java).mapIndexed{i,_ -> i}
    val nopIndices = program.filterIsInstance(Nop::class.java).mapIndexed{i,_ -> i}

    val res = (jmpIndices.map { jmpIndex ->
        runProgram(program.mapIndexed{i,inst -> if(i == jmpIndex) Nop(inst.parameter) else inst})
    } + nopIndices.map { nopIndex ->
                runProgram(program.mapIndexed { i, inst -> if (i == nopIndex) Jmp(inst.parameter) else inst })
            }).single { it.code == 0 }

    println(res.value)
}

fun runProgram(p: List<Instruction>, s: State= State(), seen:List<Int> = emptyList()): Return {
    return if (s.pointer in seen) {
        Return(0,1)
    } else if (s.pointer >= p.size){
        Return(0,0)
    } else {
        when(val ins = p[s.pointer]) {
            is Nop -> runProgram(p, s.copy(pointer = s.pointer+1), seen + s.pointer)
            is Acc -> {
                val ret = runProgram(p, s.copy(pointer = s.pointer + 1), seen + s.pointer)
                ret.copy(value = ins.parameter + ret.value)
            }
            is Jmp -> runProgram(p, s.copy(pointer = s.pointer + ins.parameter), seen + s.pointer)
        }
    }
}

data class Return( val value: Int, val code: Int)

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