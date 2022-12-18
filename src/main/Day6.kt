package main

import readInput

fun main() {

    fun <T> hasDuplicates(arr: MutableList<T>): Boolean {
        return arr.size != arr.distinct().count();
    }

    fun findPattern(input: String, consecutiveBytes: Int): Int {
        val window = input.subSequence(0, consecutiveBytes).toMutableList()
        val restPart = input.subSequence(consecutiveBytes, input.length)
        var index = consecutiveBytes
        for (new in restPart) {
            window.add(new)
            window.removeFirst()
            index++

            if (!hasDuplicates(window)) {
                return index
            }
        }

        return index
    }

    fun part1(input: List<String>): Int {
        return findPattern(input[0], 4)
    }

    fun part2(input: List<String>): Int {
        return findPattern(input[0], 14)

    }

    val testInput = readInput("Day6_test")
    check(part1(testInput) == 7)

    val input = readInput("Day6")
    println(part1(input))

    println(part2(input))
}