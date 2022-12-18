package main

import readInput

interface Node {
    val name: String
    val parent: DirNode?
    fun pp(indent: Int = 0)
}

// Do not use global variables, silly...
val totalFSsize= 70000000
val requiredFSSize= 30000000
var neededSpace = 0
var totalFizeBelow10k = 0
var dirToDeleteSize = mutableListOf<Int>()
class DirNode(
    override val name: String,
    override val parent: DirNode?,
    var children: MutableList<Node>
) : Node {
    override fun pp(indent: Int) {
        println(" ".repeat(indent) + "DirNode: $name")
        for (c in children) {
            c.pp(indent + 1)
        }
    }

    fun size(): Int {
        var result = 0
        children.forEach {
            if (it is DirNode) {
                result += it.size()
            }else if (it is FileNode){
                result+= it.memory.toInt()
            }
        }
        if (result< 100000){
            totalFizeBelow10k+=result
        }
        if (result > neededSpace){
            dirToDeleteSize.add(result)
        }
        return result
    }

}

class FileNode(
    override val name: String,
    override val parent: DirNode?,
    var memory: String
) : Node {
    override fun pp(indent: Int) {
        println(" ".repeat(indent) + "File Node: $name, $memory")
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        var lsRead = false
        val root: DirNode = DirNode("/", null, mutableListOf())
        var currentPos: DirNode? = root
        for (i in input.subList(1, input.size)) {
            val commandInfo = i.split(" ")

            if (commandInfo[0] == "$") {
                if (commandInfo[1] == "ls") { //start reading files in dir
                    lsRead = true
                    continue
                }
                if (commandInfo[1] == "cd") { // move dir
                    if (commandInfo[2] == "..") { // Move back
                        currentPos = currentPos?.parent
                    } else { // move forward
                        currentPos = currentPos?.children?.find { it.name == commandInfo[2] } as DirNode
                    }
                    continue
                }
            }
            if (lsRead == true) { // files reading mode
                if (commandInfo[0] == "dir") { // Add directory
                    currentPos?.children?.add(DirNode(commandInfo[1], currentPos, mutableListOf()))
                } else {
                    currentPos?.children?.add(FileNode(commandInfo[1], currentPos, commandInfo[0]))
                }
                continue
            }
        }
        root.pp()
        root.size()

        return totalFizeBelow10k
    }

    fun part2(input: List<String>): Int {
        var lsRead = false
        val root: DirNode = DirNode("/", null, mutableListOf())
        var currentPos: DirNode? = root
        for (i in input.subList(1, input.size)) {
            val commandInfo = i.split(" ")

            if (commandInfo[0] == "$") {
                if (commandInfo[1] == "ls") { //start reading files in dir
                    lsRead = true
                    continue
                }
                if (commandInfo[1] == "cd") { // move dir
                    if (commandInfo[2] == "..") { // Move back
                        currentPos = currentPos?.parent
                    } else { // move forward
                        currentPos = currentPos?.children?.find { it.name == commandInfo[2] } as DirNode
                    }
                    continue
                }
            }
            if (lsRead == true) { // files reading mode
                if (commandInfo[0] == "dir") { // Add directory
                    currentPos?.children?.add(DirNode(commandInfo[1], currentPos, mutableListOf()))
                } else {
                    currentPos?.children?.add(FileNode(commandInfo[1], currentPos, commandInfo[0]))
                }
                continue
            }
        }
        root.size()
        val size = root.size()

        val unusedSpace = totalFSsize - size
        neededSpace = requiredFSSize - unusedSpace
        dirToDeleteSize = mutableListOf()
        root.size()
        return dirToDeleteSize.minOf { it }
    }

    val testInput = readInput("Day7_test")
    check(part2(testInput) == 24933642)

    val input = readInput("Day7")
    println(part1(input))

    println(part2(input))
}