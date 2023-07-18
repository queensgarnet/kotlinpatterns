fun main() {
    val ibm = IBM("IBM", 120.00)
    ibm.attach(Investor("Sorros"))
    ibm.attach(Investor("Berkshire"))

    ibm.price = 120.10
    ibm.price = 121.00
    ibm.price = 120.50
    ibm.price = 120.75
}

abstract class Stock(val symbol: String, price: Number) {
    private val investors = mutableListOf<Investor>()
    var price = price
        set(value) {
            field = value
            notifyChange()
        }

    fun attach(investor: Investor) {
        investors.add(investor)
    }

    fun detach(investor: Investor) {
        investors.remove(investor)
    }

    private fun notifyChange() {
        investors.forEach {
            it.update(this)
        }
        println()
    }
}

class IBM(symbol: String, price: Number) : Stock(symbol, price)

interface IInvestor {
    fun update(stock: Stock)
}

class Investor(private val name : String) : IInvestor {
    override fun update(stock: Stock) {
        println("Notified $name of ${stock.symbol}'s change to $${stock.price}")
    }
}