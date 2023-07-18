fun main() {
    val collection = Collection(
        arrayListOf( Item("Item 0"),
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3"),
        Item("Item 4"),
        Item("Item 5"),
        Item("Item 6"),
        Item("Item 7"),
        Item("Item 8"),
        )
    )

    val iterator = collection.createIterator()

    iterator.step = 2

    println("Iterating over collection")

    do {
        println(iterator.next()!!.name)
    } while (!iterator.isDone())
}

interface IAbstractCollection {
    fun createIterator() : Iterator
}

interface IAbstractIterator {
    fun first() : Item
    fun next() : Item?
    fun isDone() : Boolean
    var currentItem: Item
}

class Collection(private val items: List<Item>) : IAbstractCollection {
    val count = items.count()
    override fun createIterator(): Iterator = Iterator(this)

    fun item(index: Int) : Item = items[index]
}

class Iterator(private val collection: Collection) : IAbstractIterator {
    private var current = 0
    var step = 1

    override fun first(): Item {
        current = 0
        return collection.item(current)
    }

    override fun next(): Item? {
        current += step
        return if (!isDone()) {
            collection.item(current)
        } else { null }
    }

    override fun isDone(): Boolean = current >= collection.count

    override var currentItem: Item
        get() = collection.item(current)
        set(_) {}

}

class Item(val name: String)