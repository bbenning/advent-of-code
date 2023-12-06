package util.longs

fun Pair<Long,Long>.sum() = this.first + this.second
fun Pair<Long,Long>.product() = this.first * this.second
fun Pair<Long,Long>.abs() = Pair(kotlin.math.abs(this.first), kotlin.math.abs(this.second))

fun Triple<Long,Long,Long>.sum() = this.first + this.second + this.third
fun Triple<Long,Long,Long>.product() = this.first * this.second * this.third
fun Triple<Long,Long,Long>.abs() = Triple(kotlin.math.abs(this.first), kotlin.math.abs(this.second), kotlin.math.abs(this.third))

operator fun Pair<Long,Long>.plus(p: Pair<Long, Long>) = Pair(this.first + p.first, this.second + p.second)
operator fun Pair<Long,Long>.minus(p: Pair<Long, Long>) = Pair(this.first - p.first, this.second - p.second)
operator fun Pair<Long,Long>.times(p: Pair<Long, Long>) = Pair(this.first * p.first, this.second * p.second)
operator fun Pair<Long,Long>.unaryMinus() = this * Pair(-1, -1)
fun Iterable<Long>.product(): Long = this.fold(1){acc, i -> acc * i}
