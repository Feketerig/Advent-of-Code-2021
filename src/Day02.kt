fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var position = 0
        val tuples = input.map {
            val tuple = it.split(" ")
            Pair(tuple[0], tuple[1].toInt())
        }
        tuples.forEach { pair ->
            when(pair.first){
                "forward" -> position += pair.second
                "down" -> depth += pair.second
                "up" -> depth -= pair.second
            }
        }
        return depth * position
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var depth = 0
        var position = 0
        val tuples = input.map {
            val tuple = it.split(" ")
            Pair(tuple[0], tuple[1].toInt())
        }
        tuples.forEach { pair ->
            when(pair.first){
                "forward" -> {
                    position += pair.second
                    depth += aim  * pair.second
                }
                "down" -> aim += pair.second
                "up" -> aim -= pair.second
            }
        }
        return depth * position
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}