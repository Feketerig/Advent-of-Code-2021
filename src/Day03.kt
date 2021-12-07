fun main() {
    fun part1(input: List<String>):Int{
        var gamma = ""
        var epsilon = ""
        for (i: Int in 0 until input[0].length){
            var ones = 0
            input.forEach {
                if (it[i] == '1'){
                    ones++
                }
            }
            if (ones > (input.size - ones)){
                gamma += "1"
                epsilon += "0"
            }else{
                gamma += "0"
                epsilon += "1"
            }
        }
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>):Int{
        var O2 = input
        var CO2 = input
        for (i: Int in 0 until input[0].length){
            var O2ones = 0
            O2.forEach {
                if (it[i] == '1'){
                    O2ones++
                }
            }
            if (O2ones >= (O2.size - O2ones)){
                if (O2.size > 1)
                    O2 = O2.filter { it[i] == '1'}
            }else{
                if (O2.size > 1)
                    O2 = O2.filter { it[i] == '0'}
            }

            var CO2ones = 0
            CO2.forEach {
                if (it[i] == '1'){
                    CO2ones++
                }
            }
            if (CO2ones >= (CO2.size - CO2ones)){
                if (CO2.size > 1)
                    CO2 = CO2.filter { it[i] == '0'}
            }else{
                if (CO2.size > 1)
                    CO2 = CO2.filter { it[i] == '1'}
            }

            if (O2.size == 1 && CO2.size == 1)
                break
        }
        return O2[0].toInt(2) * CO2[0].toInt(2)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}