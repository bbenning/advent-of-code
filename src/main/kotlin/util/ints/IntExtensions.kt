package util.ints


fun Pair<Int,Int>.sum() = this.first + this.second
fun Pair<Int,Int>.product() = this.first * this.second
fun Pair<Int,Int>.abs() = Pair(kotlin.math.abs(this.first), kotlin.math.abs(this.second))

fun Triple<Int,Int,Int>.sum() = this.first + this.second + this.third
fun Triple<Int,Int,Int>.product() = this.first * this.second * this.third
fun Triple<Int,Int,Int>.abs() = Triple(kotlin.math.abs(this.first), kotlin.math.abs(this.second), kotlin.math.abs(this.third))

operator fun Pair<Int,Int>.plus(p: Pair<Int, Int>) = Pair(this.first + p.first, this.second + p.second)
operator fun Pair<Int,Int>.minus(p: Pair<Int, Int>) = Pair(this.first - p.first, this.second - p.second)
operator fun Pair<Int,Int>.times(p: Pair<Int, Int>) = Pair(this.first * p.first, this.second * p.second)
operator fun Pair<Int,Int>.unaryMinus() = this * Pair(-1, -1)

fun Iterable<Int>.product(): Long = this.fold(1){acc, i -> acc * i}
