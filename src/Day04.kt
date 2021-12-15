import java.net.URL

fun main() {
    val input = readInput("Day04")
    val numbers = input[0].split(",").map { it.toInt() }
    val bingos: MutableList<Bingo> = mutableListOf()
    val winners: MutableList<Pair<Bingo, Int>> = mutableListOf()

    val list: MutableList<List<Pair1>> = mutableListOf()
    for (i in 2 until input.size){
        if (input[i].isNotEmpty()) {
            val row = input[i].split(" ").filter { it.isNotEmpty() }.map {
                Pair1(it.toInt(), false)
            }
            list.add(row)
        }else{
            bingos.add(Bingo(list.toMutableList(), false))
            list.clear()
        }
    }
    bingos.add(Bingo(list, false))
    numbers.forEach { number ->
        bingos.forEach { bingo ->
            if (!bingo.win) {
                val win = bingo.add(number)
                if (win != null) {
                    bingo.win = true
                    winners.add(Pair(bingo, number))
                }
            }
        }
    }
    println(winners.first().first.sum() * winners.first().second)
    println(winners.last().first.sum() * winners.last().second)
}

data class Bingo(
    var matrix: List<List<Pair1>>,
    var win: Boolean
){
    fun add(number: Int): Int?{
        matrix.forEach {
            it.forEach {
                if (it.number == number){
                    it.bool = true
                }
            }
        }
        return if(check()){
            sum() * number
        }else{
            null
        }
    }

    fun check(): Boolean{
        return rowCheck() || columnCheck()
    }

    fun rowCheck(): Boolean{
        matrix.forEach {
            val list = it.filter { !it.bool }
            if (list.isEmpty()){
                return true
            }
        }
        return false
    }

    fun columnCheck(): Boolean {
        for (i in 0 until matrix[0].size){
            val list1 = mutableListOf<Boolean>()
            matrix.forEach {
                list1.add(it[i].bool)
            }
            val list = list1.filter { !it }
            if (list.isEmpty()){
                return true
            }
        }
        return false
    }

    fun sum(): Int{
        var sum = 0
        matrix.forEach {
            it.forEach {
                if (!it.bool) {
                    sum += it.number
                }
            }
        }
        return sum
    }
}

data class Pair1(val number:Int, var bool:Boolean)

/*fun main() {
    println(Day4Solution.solvePart1())
    //33348
    println(Day4Solution.solvePart2())
    //8112
}

object Day4Solution {
    val NumberRegex = "([0-9]+)".toRegex()

    data class Entry(var value: Int, var marked: Boolean = false) {
        fun update(number: Int) {
            if (number == value) marked = true
        }
    }

    data class Board(var lines: List<String>) {
        private val m: List<List<Entry>> = List(5) { l ->
            NumberRegex.findAll(lines[l]).map(MatchResult::value).map(String::toInt).map(::Entry).toList()
        }

        private fun hasRow(): Boolean {
            return (0..4).any { row ->
                (0..4).all { column ->
                    m[row][column].marked
                }
            }
        }

        private fun hasColumn(): Boolean {
            return (0..4).any { column ->
                (0..4).all { row ->
                    m[row][column].marked
                }
            }
        }

        fun sumUnmarked(): Int {
            return m.flatten().filter { entry -> !entry.marked }.sumOf { entry -> entry.value }
        }

        fun isWinner() = hasRow() || hasColumn()

        fun update(number: Int) {
            m.flatten().forEach { entry -> entry.update(number) }
        }
    }

     fun solvePart1(): String {
        val lines = readInput("Day04").filter(String::isNotEmpty)

        val numbers = NumberRegex.findAll(lines[0]).map(MatchResult::value).map(String::toInt).toList()

        val boards = lines.drop(1).windowed(5, 5).map(::Board)

        val winners = mutableListOf<Pair<Int, Board>>()

        for (number in numbers) {
            boards.forEach { board ->
                if (!board.isWinner()) {
                    board.update(number)

                    if (board.isWinner()) {
                        winners += number to board
                    }
                }
            }
        }

        val (number, board) = winners.first()

        return "${board.sumUnmarked() * number}"
    }

    fun solvePart2(): String {
        val lines = readInput("Day04").filter(String::isNotEmpty)

        val numbers = NumberRegex.findAll(lines[0]).map(MatchResult::value).map(String::toInt).toList()

        val boards = lines.drop(1).windowed(5, 5).map(::Board)

        val winners = mutableListOf<Pair<Int, Board>>()

        for (number in numbers) {
            boards.forEach { board ->
                if (!board.isWinner()) {
                    board.update(number)

                    if (board.isWinner()) {
                        winners += number to board
                    }
                }
            }
        }

        val (number, board) = winners.last()

        return "${board.sumUnmarked() * number}"
    }
}*/