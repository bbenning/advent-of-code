package nl.bbenning.advent._2015

import java.io.File

object Puzzle02 {
    @JvmStatic
    fun main(args: Array<String>) {

        val dimensionsList = File("./src/main/resources/inputs/2015/input02.txt").readLines()

        val totalSquarFeetOfWrappingPaper = dimensionsList.map{
            val dimensions = it.split("x")
            val l = dimensions[0].toInt()
            val w = dimensions[1].toInt()
            val h = dimensions[2].toInt()

            val lw = l*w
            val wh = w*h
            val hl = h*l

            val minSide = listOf(lw, wh, hl).min()

            2*l*w + 2*w*h + 2*h*l + minSide
        }.sum()

        val totalRibbonLength = dimensionsList.map{
            val dimensions = it.split("x")
            val l = dimensions[0].toInt()
            val w = dimensions[1].toInt()
            val h = dimensions[2].toInt()

            val sides = listOf(l, w, h).sorted()

            2*sides[0] + 2*sides[1] + l * w * h
        }.sum()


        println("Total square feet of wrapping paper | answer a: $totalSquarFeetOfWrappingPaper")
        println("Total length of ribbons | answer b: $totalRibbonLength")
    }
}