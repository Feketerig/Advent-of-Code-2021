fun main() {
    val input = readInput("Day07")[0].split(",").map { it.toInt() }

    fun part1(input: List<Int>): Int {
        val distinct = input.distinct()

        val list: MutableList<Int> = mutableListOf()
        distinct.forEach { align ->
            var sum = 0
            input.forEach { pos ->
                sum += if (pos > align) {
                    pos - align
                } else {
                    align - pos
                }
            }
            list.add(sum)
        }
        return list.minOf { it }
    }

    fun fuelSum(distance: Int): Int{
        var sum = 0
        for (i in 1..distance){
            sum += i
        }
        return sum
    }

    fun part2(input: List<Int>): Int {
        val distinct = input.distinct()

        val list: MutableList<Pair<Int, Int>> = mutableListOf()
        distinct.forEach { align ->
            var sum = 0
            input.forEach { pos ->
                sum += if (pos > align) {
                    fuelSum(pos - align)
                } else {
                    fuelSum(align - pos)
                }
            }
            list.add(Pair(align, sum))
        }
        val min = list.find { it.second == list.minOf { it.second }}!!.first
        val newlist: MutableList<Pair<Int, Int>> = mutableListOf()
        for (align in min - 20 .. min + 20){
            var sum = 0
            input.forEach { pos ->
                sum += if (pos > align) {
                    fuelSum(pos - align)
                } else {
                    fuelSum(align - pos)
                }
            }
            newlist.add(Pair(align, sum))
        }
        return newlist.minOf { it.second }
    }

    println(part1(input))
    println(part2(input))
}