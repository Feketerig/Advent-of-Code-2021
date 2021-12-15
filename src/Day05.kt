fun main() {
    val input = readInput("Day05")
    val lines = input.map {
        val coords = it.split(" -> ").map { it.split(",").map{it.toInt()} }
        Line(coords[0][0], coords[0][1], coords[1][0], coords[1][1])
    }
    var maxX = 0
    var maxY = 0
    lines.forEach {
        maxX = if (it.startX > maxX){
            it.startX
        }else if(it.endX > maxX){
            it.endX
        }else{
            maxX
        }
        maxY = if (it.startY > maxY){
            it.startY
        }else if(it.endY > maxY){
            it.endY
        }else{
            maxY
        }
    }
    val rowHorizontalAndVertical = MutableList(maxX + 1) { 0 }
    val matrixHorizontalAndVertical = MutableList(maxY + 1) { rowHorizontalAndVertical.toMutableList() }
    val horizontalOrVerticalLines = lines.filter { it.isHorizontalOrVertical() }
    horizontalOrVerticalLines.forEach { line ->
        val coords = line.toCoords()
        coords.forEach {
            matrixHorizontalAndVertical[it.second][it.first] += 1
        }
    }
    println(matrixHorizontalAndVertical.flatten().count { it >= 2 })

    val row = MutableList(maxX + 1) { 0 }
    val matrix = MutableList(maxY + 1) { row.toMutableList() }
    lines.forEach { line ->
        val coords = line.toCoords()
        coords.forEach {
            matrix[it.second][it.first] += 1
        }
    }
    println(matrix.flatten().count { it >= 2 })
}

data class Line(
    val startX: Int,
    val startY: Int,
    val endX: Int,
    val endY: Int
){
    fun isHorizontalOrVertical(): Boolean{
        return startX == endX || startY == endY
    }

    fun toCoords(): MutableList<Pair<Int, Int>>{
        val coords: MutableList<Pair<Int, Int>> = mutableListOf()
        if (startX == endX){
            if (endY > startY){
                for (i in 0 .. endY - startY){
                    coords.add(Pair(startX, startY + i))
                }
            }else{
                for (i in 0 .. startY - endY){
                    coords.add(Pair(startX, startY - i))
                }
            }
        }else if(startY == endY){
            if (endX > startX){
                for (i in 0 .. endX - startX){
                    coords.add(Pair(startX + i, startY))
                }
            }else{
                startX - endX
                for (i in 0 .. startX - endX){
                    coords.add(Pair(startX - i, startY))
                }
            }
        }else{
            if (endX > startX && endY > startY){
                for (i in 0 .. endX - startX){
                    coords.add(Pair(startX + i, startY + i))
                }
            }else if(startX > endX && endY > startY){
                for (i in 0 .. startX - endX){
                    coords.add(Pair(startX - i, startY + i))
                }
            }else if(endX > startX && startY > endY){
                for (i in 0 .. endX - startX){
                    coords.add(Pair(startX + i, startY - i))
                }
            }else if(startX > endX && startY > endY){
                for (i in 0 .. startX - endX){
                    coords.add(Pair(startX - i, startY - i))
                }
            }
        }
        return coords
    }
}