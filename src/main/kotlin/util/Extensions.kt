package util

fun Any?.printIt() = println(this)


/**
 * Replaces the current item in position index with the given item.
 */
fun <T> List<T>.updated(index: Int, item: T): List<T> = toMutableList().apply { this[index] = item }

fun <T> List<T>.updatedLast(item: T): List<T> = this.updated(this.size - 1, item)
fun <T> List<T>.updatedFirst(item: T): List<T> = this.updated(0, item)

// Not quite sure how to do this effectively
fun <A>memoizedRepeat(repeats: Long, start: A, func: (A) -> A): A {

    val historyList = mutableListOf<A>()
    val historySet = mutableSetOf<A>()

    var result = start
    var count = 0L
    while(count < repeats) {
        if(result !in historySet) {
            historyList.add(result)
            historySet.add(result)
            result = func(result)
        } else {
            // now we can calculate until the end.
            val idxOfRepeat = historySet.indexOf(result)
            val lengthOfRepeat = count.toInt() - idxOfRepeat // count should never get into ulong territory
            val stillNeedToGo = ((repeats - idxOfRepeat.toLong()).rem(lengthOfRepeat.toLong())).toInt()
            return historyList[idxOfRepeat + stillNeedToGo]!!
        }

        count++
    }

    return result
}