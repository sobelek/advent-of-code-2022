package main

import readInput
import java.util.stream.Collectors.toList

fun main() {
    fun getPriority(letter: Char): Int {
        if (letter.isUpperCase()) {
            return letter.code - 38
        }
        if (letter.isLowerCase()) {
            return letter.code - 96
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        var totalPriority: Int = 0
        for (i in input) {
            val leftCompartment = i.substring(0, i.length / 2)
            val rightCompartment = i.substring(i.length / 2)
            var duplicate: Char = '.'
            leftCompartment.forEach { if (rightCompartment.contains(it)) duplicate = it }
            val priority = getPriority(duplicate)

            println("First part: $leftCompartment second: $rightCompartment duplicate: $duplicate priority: $priority")
            totalPriority += priority
        }
        return totalPriority
    }

    fun part2(input: List<String>): Int {
        var totalPriority = 0
        val grouppedInput = input.chunked(3)
        for (group in grouppedInput) {
                group[0].toList().stream()
                    .filter(group[1].toList()::contains)
                    .filter(group[2].toList()::contains)
                    .collect(toList())[0]
                    .let { getPriority(it) }
                    .let { totalPriority+=it }
        }
        return totalPriority
    }

    val testInput = readInput("Day3_test")
    check(part2(testInput) == 70)

    val input = readInput("Day3")
    println(part1(input))

    println(part2(input))
}