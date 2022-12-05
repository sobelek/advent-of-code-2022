package main

import readInput

fun main() {

    fun createCrateStacks(crates: List<String>): MutableMap<Int, ArrayDeque<Char>> {
        val indexMap = hashMapOf<Int, Int>().also { map ->
            crates.last().forEachIndexed { index, c ->
                if (c.isDigit()) {
                    map[index] = c.digitToInt()
                }
            }
        }
        val cratesStacks = hashMapOf<Int, ArrayDeque<Char>>()
        crates.dropLast(1).forEachIndexed { _, s ->
            s.forEachIndexed { index, crate ->
                if (crate.isLetter()) {
                    val position = indexMap[index]!!
                    if (cratesStacks.containsKey(position)){
                        cratesStacks[position]!!.addLast(crate)
                    }else{
                        cratesStacks[position] = ArrayDeque<Char>().also { it.addLast(crate) }
                    }
                }
            }
        }
        return cratesStacks
    }

    fun part1(input: List<String>): String {
        val crates = input.takeWhile { it.isNotEmpty() }
        val commands = input.takeLastWhile { it.isNotEmpty() }
        val cratesStacks = createCrateStacks(crates)

        commands.forEach {
            val (move, from, to) = it.split(" ")
                .filter { it.toIntOrNull() != null }
                .map { it.toInt() }
            for(i in 1..move){
                cratesStacks[from]!!.removeFirst().also { cratesStacks[to]!!.addFirst(it) }
            }
        }

        var result= ""
        cratesStacks.forEach { (t, u) -> result += u.first() }
        return result
    }

    fun part2(input: List<String>): String {
        val crates = input.takeWhile { it.isNotEmpty() }
        val commands = input.takeLastWhile { it.isNotEmpty() }
        val cratesStacks = createCrateStacks(crates)

        commands.forEach {
            val (move, from, to) = it.split(" ")
                .filter { it.toIntOrNull() != null }
                .map { it.toInt() }
            val tempStack = ArrayDeque<Char>()
            for(i in 1..move){
                cratesStacks[from]!!.removeFirst().also { tempStack.addFirst(it) }
            }
            for(i in 1..move){
                tempStack.removeFirst().also { cratesStacks[to]!!.addFirst(it) }
            }
        }
        var result= ""
        cratesStacks.forEach { (_, u) -> result += u.first() }
        return result
    }

    val testInput = readInput("Day5_test")
    check(part2(testInput) == "MCD")

    val input = readInput("Day5")
    println(part1(input))

    println(part2(input))
}