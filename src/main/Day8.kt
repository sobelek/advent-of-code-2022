package main

import readInput

fun main() {


    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day1_test")
    check(part1(testInput) == 1)

    val input = readInput("Day1")
    println(part1(input))

    println(part2(input))
}