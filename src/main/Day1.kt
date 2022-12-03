package main

import readInput

fun main() {
    fun getCaloriesByElf(input: List<String>): MutableMap<Int, Int>{
        var elfNumber = 1
        var elfsCalories = hashMapOf<Int, Int>()

        for (i in input){
            //next elf
            if (i == ""){
                elfNumber++
            }else{
                when(val elfTotalCalories = elfsCalories[elfNumber]){
                    null -> elfsCalories[elfNumber] = i.toInt()
                    else -> elfsCalories[elfNumber] = elfTotalCalories + i.toInt()
                }
            }
        }
        return elfsCalories
    }
    fun part1(input: List<String>): Int {
        var elfsCalories = getCaloriesByElf(input)
        return elfsCalories.maxOf { it.value }
    }

    fun part2(input: List<String>): Int {
        var elfsCalories = getCaloriesByElf(input).toList().map { it.second }.sorted().takeLast(3)
        return elfsCalories.sum()
    }

    val testInput = readInput("Day1_test")
    check(part1(testInput) == 15462)

    val input = readInput("Day1")
    println(part1(input))

    println(part2(input))
}