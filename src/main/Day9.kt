package main

import readInput
import kotlin.math.abs

data class Position(var x : Int, var y : Int)

fun main() {

    fun moveHead(direction: String, headPosition: Position) {
        when(direction){
            "R" -> headPosition.x = headPosition.x + 1
            "L" -> headPosition.x = headPosition.x - 1
            "U" -> headPosition.y = headPosition.y + 1
            "D" -> headPosition.y = headPosition.y - 1
        }

    }

    fun isRightUpMove(xdiff: Int, ydiff: Int): Boolean {
        if (xdiff==2 && ydiff == 1){
            return true
        }
        if (xdiff==2 && ydiff == 2){
            return true
        }
        if (xdiff == 1 && ydiff == 2){
            return true
        }
        return false
    }
    fun isRightDownMove(xdiff: Int, ydiff: Int): Boolean {
        if (xdiff==1 && ydiff == -2){
            return true
        }
        if (xdiff == 2 && ydiff == -1){
            return true
        }
        if (xdiff == 2 && ydiff == -2){
            return true
        }
        return false
    }
    fun isLeftUpMove(xdiff: Int, ydiff: Int): Boolean {
        if (xdiff==-1 && ydiff == 2){
            return true
        }
        if (xdiff ==-2 && ydiff == 1){
            return true
        }
        if (xdiff ==-2 && ydiff == 2){
            return true
        }
        return false
    }
    fun isLeftDownMove(xdiff: Int, ydiff: Int): Boolean {
        if (xdiff==-2 && ydiff == -1){
            return true
        }
        if (xdiff == -1 && ydiff == -2){
            return true
        }
        if (xdiff ==-2 && ydiff == -2){
            return true
        }
        return false
    }
    fun updateTail(headPosition: Position, tailPosition: Position) {

        val xdiff = headPosition.x- tailPosition.x
        val xsum = headPosition.x+ tailPosition.x
        val ydiff = headPosition.y- tailPosition.y
        val ysum = headPosition.y- tailPosition.y

        val isDiagonal = xdiff != 0 && ydiff != 0
        if (isDiagonal){// diagonal Move
            if (abs(xdiff) > 1 || abs(ydiff)> 1){ // should move
                if (isRightUpMove(xdiff, ydiff)){// up right
                    tailPosition.x = tailPosition.x+1
                    tailPosition.y = tailPosition.y +1
                }else if(isRightDownMove(xdiff,  ydiff)){ // down right
                    tailPosition.x = tailPosition.x + 1
                    tailPosition.y = tailPosition.y - 1
                }else if(isLeftUpMove(xdiff, ydiff)){ // up left
                    tailPosition.x = tailPosition.x - 1
                    tailPosition.y = tailPosition.y + 1
                }else if(isLeftDownMove(xdiff, ydiff)){ // down left
                    tailPosition.x = tailPosition.x -1
                    tailPosition.y = tailPosition.y - 1
                }
            }
        }else{// straight move
            when (xdiff){
                2 -> tailPosition.x = tailPosition.x + 1
                -2 -> tailPosition.x = tailPosition.x - 1
            }
            when (ydiff){
                2 -> tailPosition.y = tailPosition.y + 1
                -2 -> tailPosition.y = tailPosition.y - 1
            }

        }

    }
    fun printBoard(headPosition: Position, tailPosition: Position){
        val emptyBoard = MutableList(10) { MutableList(10) { "." } }
        emptyBoard[headPosition.y][headPosition.x] = "H"
        emptyBoard[tailPosition.y][tailPosition.x] = "T"

        for (row in emptyBoard.reversed()){
            for (element in row){
                print(element)
            }
            println()
        }
        println()
    }
    fun printBoard(tails: List<Position>){
        val emptyBoard = MutableList(10) { MutableList(10) { "." } }
        emptyBoard[tails[0].y][tails[0].x] = "H"
        for (i in 1..9){
            emptyBoard[tails[i].y][tails[i].x] = i.toString()
        }

        for (row in emptyBoard.reversed()){
            for (element in row){
                print(element)
            }
            println()
        }
        println()
    }

    fun part1(input: List<String>): Int {
        val i: List<Pair<String, Int>> = input.map { it.split(" ") }.map { Pair(it[0].toString(), it[1].toInt()) }
        var visited = mutableSetOf<Pair<Int, Int>>()

        var headPosition = Position(0, 0) // Pair<x pos, y pos>
        var tailPosition = Position(0, 0)
        visited.add(Pair(tailPosition.x, tailPosition.y))
        i.forEach {
            for (step in 1..it.second){
                moveHead(it.first, headPosition)
                updateTail(headPosition, tailPosition)
                visited.add(Pair(tailPosition.x, tailPosition.y))
                // printBoard(headPosition, tailPosition)

            }

        }
        println(visited)
        println(visited.size)
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val i: List<Pair<String, Int>> = input.map { it.split(" ") }.map { Pair(it[0].toString(), it[1].toInt()) }
        var visited = mutableSetOf<Pair<Int, Int>>()


        val tails = MutableList(10) { Position(0, 0) }
        visited.add(Pair(tails[9].x, tails[9].y))

        i.forEach {
            for (step in 1..it.second){
                moveHead(it.first, tails[0])
                for (tail in 1..9){
                    updateTail(tails[tail-1], tails[tail])
                }
                visited.add(Pair(tails[9].x, tails[9].y))
                // printBoard(tails)

            }

        }
        println(visited)
        println(visited.size)
        return visited.size
    }

    val testInput = readInput("Day9_test")
    // check(part2(testInput) == 13)

    val input = readInput("Day9")
    println(part1(input))

    println(part2(input))
}