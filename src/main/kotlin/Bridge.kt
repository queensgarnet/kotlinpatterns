fun main() {
    val customers = Customers(CustomersData("New York"))
    customers.show();
    customers.next();
    customers.show();
    customers.next();
    customers.show();
    customers.add("Nick Fury");
    customers.showAll();
}

abstract class CustomersBase(private val dataObject: DataObject) {
    fun next() = dataObject.nextRecord()
    fun prior() = dataObject.priorRecord()
    fun add(name: String) = dataObject.addRecord(name)
    fun delete(name: String) = dataObject.deleteRecord(name)
    fun show() = dataObject.showRecord()
    open fun showAll() = dataObject.showAllRecords()
}

class Customers(dataObject: DataObject): CustomersBase(dataObject) {
    override fun showAll() {
        println("")
        println("-------------------")
        super.showAll()
        println("-------------------")
    }
}

abstract class DataObject {
    abstract fun nextRecord()
    abstract fun priorRecord()
    abstract fun addRecord(name: String)
    abstract fun deleteRecord(name: String)
    abstract fun getCurrentRecord(): String
    abstract fun showRecord()
    abstract fun showAllRecords()
}

class CustomersData(private val city: String) : DataObject() {
    private var current = 0
    private val customers = mutableListOf(
        "Tony Stark",
        "Bruce Banner",
        "Natasha Romanoff",
        "Peter Parker",
        "Peter Quill",
    )

    override fun nextRecord() {
        if (current < customers.count()-1) {
            current++
        }
    }

    override fun priorRecord() {
        if (current > 0)  {
            current--
        }
    }

    override fun addRecord(name: String) {
        customers.add(name)
    }

    override fun deleteRecord(name: String) {
        customers.remove(name)
    }

    override fun getCurrentRecord() = customers[current]

    override fun showRecord() = println(customers[current])

    override fun showAllRecords() {
        println("Customer City: $city")
        customers.forEach { println(" $it") }
    }
}