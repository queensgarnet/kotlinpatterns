fun main() {
    val larry = Director()
    val sam = VicePresident()
    val tammy = President()

    larry.setApprover(sam)
    sam.setApprover(tammy)

    var purchase = Purchase(2034, 350.00, "Supplies")
    larry.processRequest(purchase)

    purchase = Purchase(2035, 3050.50, "Project A")
    larry.processRequest(purchase)

    purchase = Purchase(2035, 13050.90, "Project B")
    larry.processRequest(purchase)
}

abstract class Approver {
    protected var successor: Approver? = null

    fun setApprover(approver: Approver) {
        successor = approver
    }

    abstract fun processRequest(purchase: Purchase)
}

class VicePresident : Approver() {
    override fun processRequest(purchase: Purchase) {
        if (purchase.amount < 5000.00) {
            println("${this::class.simpleName} approved request# ${purchase.number}")
        } else successor?.processRequest(purchase)
    }
}

class President : Approver() {
    override fun processRequest(purchase: Purchase) {
        if (purchase.amount < 10000.00) {
            println("${this::class.simpleName} approved request# ${purchase.number}")
        } else {
            println("Request# ${purchase.number} requires an executive meeting!")
        }
    }
}

class Director : Approver() {
    override fun processRequest(purchase: Purchase) {
        if (purchase.amount < 1000.00) {
            println("${this::class.simpleName} approved request# ${purchase.number}")
        } else successor?.processRequest(purchase)
    }
}

class Purchase(val number: Int, val amount: Double, val purchase: String)