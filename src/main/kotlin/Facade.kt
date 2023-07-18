fun main() {
    val mortgage = Mortgage()

    val customer = Customer("Ann McKinsey")
    val eligible = if (mortgage.isEligible(customer, 125000)) "Approved" else "Rejected"

    println("\n${customer.name} has been $eligible ")
}

class Bank {
    fun hasSufficientSavings(customer: Customer, amount: Number): Boolean {
        println("Check bank for ${customer.name}")
        return true
    }
}

class Credit {
    fun hasGoodCredit(customer: Customer): Boolean {
        println("Check credit for ${customer.name}")
        return true
    }
}

class Loan {
    fun hasNoBadLoans(customer: Customer): Boolean {
        println("Check loans for ${customer.name}")
        return true
    }
}

class Customer(val name: String)

class Mortgage {
    private val bank = Bank()
    private val credit = Credit()
    private val loan = Loan()

    fun isEligible(customer: Customer, amount: Number): Boolean {
        println("${customer.name} applies for $$amount loan\n")
        return bank.hasSufficientSavings(customer, amount)
                && credit.hasGoodCredit(customer)
                && loan.hasNoBadLoans(customer)
    }
}