fun main() {
    val input = readInput("Day06")[0].split(",").map { it.toInt() }

    val fishes = mutableListOf<Fish>()

    for (i in 0 .. 6){
        val fish = input.count { it == i }.toLong()
        if (fish > 0){
            fishes.add(Fish(i, fish))
        }
    }

    val fish = Lanternfish(fishes.toMutableList())

    for (i in 0 until 256){
        fish.step()
        println(i)
    }

    //println(fish.fishes)
    println(fish.sum())
}

class Lanternfish(
    val fishes: MutableList<Fish>
){
    fun step(){
        val newfishes = mutableListOf<Fish>()
        var zeroage:Fish? = null
        fishes.forEach { fish ->
            if (fish.age == 0){
                newfishes.add(Fish(6, fish.count))
                newfishes.add(Fish(8, fish.count))
                zeroage = fish
            }else{
                fish.age -= 1
            }
        }
        if (zeroage != null) {
            fishes.remove(zeroage)
        }
        newfishes.forEach { newfish ->
            if (newfish.age == 6){
                val age6 = fishes.find { it.age == 6 }
                if (age6 != null){
                    age6.count += newfish.count
                }else{
                    fishes.add(newfish)
                }
            }else {
                fishes.add(newfish)
            }
        }
    }

    fun sum(): Long{
        var sum = 0L
        fishes.forEach {
            sum += it.count
        }
        return sum
    }
}

data class Fish(
    var age: Int,
    var count: Long
)