fun main() {
    val categories : DataAccessor = Categories()
    categories.run(5)

    val products : DataAccessor = Products()
    products.run(3)
}

abstract class DataAccessor {
    abstract fun connect()
    abstract fun select()
    abstract fun process(top: Int)
    abstract fun disconnect()

    fun run(top: Int) {
        connect()
        select()
        process(top)
        disconnect()
    }
}

class Categories : DataAccessor() {
    private var categories = mutableListOf<String>()

    override fun connect() {
        categories = mutableListOf()
    }

    override fun select() {
        categories.add("Red")
        categories.add("Green")
        categories.add("Blue")
        categories.add("Yellow")
        categories.add("Purple")
        categories.add("White")
        categories.add("Black")
    }

    override fun process(top: Int) {
        println("Categories ---- ")

        for (i in 0..top) {
            println(" ${categories[i]}")
        }
        println()
    }

    override fun disconnect() {
        categories.clear()
    }
}

class Products : DataAccessor() {
    private var products = mutableListOf<String>()

    override fun connect() {
        products = mutableListOf()
    }

    override fun select() {
        products.add("Car")
        products.add("Bike")
        products.add("Boat")
        products.add("Truck")
        products.add("Moped")
        products.add("Rollerskate")
        products.add("Stroller")
    }

    override fun process(top: Int) {
        println("Products ---- ")

        for (i in 0..top) {
            println(" ${products[i]}")
        }
        println()
    }

    override fun disconnect() {
        products.clear()
    }
}