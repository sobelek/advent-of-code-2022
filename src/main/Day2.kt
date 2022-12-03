package main

import readInput

fun main() {

    fun winScore(opponent: String, player: String): Int {
        if (opponent == player){
            return 3
        }
        var score: Int = 0
        when(player){
            "ROCK"->{
                if (opponent == "SCISSORS") score= 6
                if (opponent == "PAPER") score= 0
            }
            "PAPER"->{
                if (opponent == "SCISSORS") score= 0
                if (opponent == "ROCK") score= 6
            }
            "SCISSORS"->{
                if (opponent == "ROCK") score= 0
                if (opponent == "PAPER") score= 6
            }
        }
        return score
    }
    fun getFigureFromResult(opponent: String, expectedOutcome: String): String {
        if (expectedOutcome == "DRAW"){
            return opponent
        }
        if (expectedOutcome == "WIN"){
            return when(opponent){
                "ROCK" -> "PAPER"
                "PAPER" -> "SCISSORS"
                "SCISSORS" -> "ROCK"
                else -> "NONE"
            }
        }
        if (expectedOutcome == "LOSE"){
            return when(opponent){
                "ROCK" -> "SCISSORS"
                "PAPER" -> "ROCK"
                "SCISSORS" -> "PAPER"
                else -> "NONE"
            }
        }
        return "NONE"
    }
    fun Char.mapToFigure(): String {
        return when (this) {
            'X', 'A' -> "ROCK"
            'Y', 'B' -> "PAPER"
            'Z', 'C' -> "SCISSORS"
            else -> "NONE"
        }
    }
    fun Char.mapToOutcome(): String {
        return when (this) {
            'X' -> "LOSE"
            'Y' -> "DRAW"
            'Z' -> "WIN"
            else -> "NONE"
        }
    }

    fun String.mapToScore(): Int{
        return when(this){
            "ROCK" -> 1
            "PAPER" -> 2
            "SCISSORS" -> 3
            else -> 9999
        }
    }

    fun part1(input: List<String>): Int {
        var totalScore: Int = 0
        for (round in input) {
            val opponent = round[0].mapToFigure()
            val player = round[2].mapToFigure()
            println("opponent: $opponent player: $player winScore: ${winScore(opponent, player)}, figureScore: ${player.mapToScore()}")
            totalScore += winScore(opponent, player)
            totalScore += player.mapToScore()
        }
        return totalScore
    }



    fun part2(input: List<String>): Int {
        var totalScore: Int = 0
        for (round in input) {
            val opponent = round[0].mapToFigure()
            val expectedOutcome = round[2].mapToOutcome()
            val player = getFigureFromResult(opponent, expectedOutcome)
            println("opponent: $opponent player: $player winScore: ${winScore(opponent, player)}, figureScore: ${player.mapToScore()}")
            totalScore += winScore(opponent, player)
            totalScore += player.mapToScore()

        }
        return totalScore
    }

    val testInput = readInput("Day2_test")
    check(part1(testInput) == 15)

    val input = readInput("Day2")
    println(part1(input))

    println(part2(input))
}