fun main() {
    val account = Account("Jim Johnson")

    account.deposit(500.0)
    account.deposit(300.0)
    account.deposit(550.0)
    account.payInterest()
    account.withdraw(2000.0)
    account.withdraw(1100.0)
}

abstract class State(open var balance: Double, val account: Account) {
    abstract val interest: Number
    abstract val upperLimit: Number
    abstract val lowerLimit: Number

    abstract fun deposit(amount: Double)
    abstract fun withdraw(amount: Double)
    abstract fun payInterest()
}

class RedState(state : State) : State(state.balance, state.account) {
    override val interest = 0.0
    override val upperLimit = 0.0
    override val lowerLimit = -100.0
    //private val serviceFee = 15.00

    override fun deposit(amount: Double) {
        balance += amount
        stateCheck()
    }

    override fun withdraw(amount: Double) {
        //amount -= serviceFee
        println("No funds available for withdrawal")
    }

    override fun payInterest() {
        // no interest on this state
    }

    private fun stateCheck() {
        if (balance > upperLimit) {
            account.state
        }
    }
}

class SilverState(balance: Double, account: Account) : State(balance, account) {

    constructor(state: State): this(state.balance, state.account)

    override val interest = 0.0
    override val upperLimit = 1000.0
    override val lowerLimit = 0.0

    override fun deposit(amount: Double) {
        balance += amount
        stateCheck()
    }

    override fun withdraw(amount: Double) {
        balance -= amount
        stateCheck()
    }

    override fun payInterest() {
        balance += balance * interest
        stateCheck()
    }

    private fun stateCheck() {
        if (balance < lowerLimit) {
            account.state = RedState(this)
        } else if (balance > upperLimit) {
            account.state = GoldState(this)
        }
    }
}

class GoldState(state: State) : State(state.balance, state.account) {
    override val interest = 0.05
    override val upperLimit = 1000000.0
    override val lowerLimit = 1000.0

    override fun deposit(amount: Double) {
        balance += amount
        stateCheck()
    }

    override fun withdraw(amount: Double) {
        balance -= amount
        stateCheck()
    }

    override fun payInterest() {
        balance += balance * interest
        stateCheck()
    }

    private fun stateCheck() {
        if (balance < 0.0) {
            account.state = RedState(this)
        } else if (balance < lowerLimit) {
            account.state = SilverState(this)
        }
    }
}

class Account(val name: String) {
    var state: State = SilverState(0.0, this)

    fun deposit(amount : Double) {
        state.deposit(amount)
        println("Deposited $$amount ----")
        println(" Balance =  $${state.balance}")
        println(" Status  = ${state::class.simpleName}\n")
    }

    fun withdraw(amount : Double) {
        state.withdraw(amount)
        println("Withdrew $$amount ----")
        println(" Balance =  $${state.balance}")
        println(" Status  = ${state::class.simpleName}\n")
    }

    fun payInterest() {
        state.payInterest()
        println("Interest paid ----")
        println(" Balance =  $${state.balance}")
        println(" Status  = ${state::class.simpleName}\n")
    }
}