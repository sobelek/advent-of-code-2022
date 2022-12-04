package main

import readInput

fun main() {
    fun getElfRangeList(elfRange: String): List<Int>{
        val range = elfRange.split('-').map { it.toInt() }
        val result = mutableListOf<Int>()
        for (i in range[0] .. range[1]) result.add(i)
        return result
    }

    fun part1(input: List<String>): Int {
        var totalOverlaps = 0
        for (elfsPair in input) {
            val elf1 = elfsPair.split(',')[0]
            val elf2 = elfsPair.split(',')[1]
            val elf1Range = getElfRangeList(elf1)
            val elf2Range = getElfRangeList(elf2)

            if (elf1Range.containsAll(elf2Range) || elf2Range.containsAll(elf1Range)) totalOverlaps++

        }
        return totalOverlaps
    }

    fun part2(input: List<String>): Int {
        var totalOverlaps = 0
        for (elfsPair in input) {
            val elf1 = elfsPair.split(',')[0]
            val elf2 = elfsPair.split(',')[1]
            val elf1Range = getElfRangeList(elf1)
            val elf2Range = getElfRangeList(elf2)

            if (elf1Range.any{it in elf2Range} || elf2Range.any{it in elf1Range}) totalOverlaps++

        }
        return totalOverlaps
    }

    val testInput = readInput("Day4_test")
    check(part1(testInput) == 3)

    val input = readInput("Day4")
    println(part1(input))

    println(part2(input))
}