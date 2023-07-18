fun main() {
    val studentRecords = SortedList()

    studentRecords.add("Samual")
    studentRecords.add("Jimmy")
    studentRecords.add("Sandra")
    studentRecords.add("Vivek")
    studentRecords.add("Anna")

    studentRecords.setSortStrategy(QuickSort())
    studentRecords.sort()

    studentRecords.setSortStrategy(ShellSort())
    studentRecords.sort()

    studentRecords.setSortStrategy(MergeSort())
    studentRecords.sort()
}

abstract class SortStrategy {
    abstract fun sort(list: List<String>) : List<String>
}

class QuickSort : SortStrategy() {
    override fun sort(list: List<String>) : List<String>{
        println("Quick sorted list")
        return list.sortedBy { it }
    }
}

class ShellSort : SortStrategy() {
    override fun sort(list: List<String>) : List<String>{
        println("Shell sorted list")
        return list
    }
}

class MergeSort : SortStrategy() {
    override fun sort(list: List<String>) : List<String>{
        println("Merge sorted list")
        return list
    }
}

class SortedList {
    private var list = mutableListOf<String>()
    private var strategy: SortStrategy? = null

    fun add(name: String) {
        list.add(name)
    }

    fun setSortStrategy(sortStrategy: SortStrategy) {
        strategy = sortStrategy
    }

    fun sort() {
        list = strategy!!.sort(list).toMutableList()
        list.forEach { println(" $it") }
        println()
    }
}