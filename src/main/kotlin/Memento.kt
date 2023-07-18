fun main() {
    val salesProspect = SalesProspect("Noel van Halen", "(412) 256-0990", 25000.0)

    val prospectMemory = ProspectMemory(salesProspect.saveMemento())

    salesProspect.name = "Leo Welch"
    salesProspect.phone = "(310) 209-7111"
    salesProspect.budget = 1000000.0

    salesProspect.restoreMemento(prospectMemory.memento)
}

class SalesProspect(name: String, phone: String, budget: Number) {
     var name = name
        set(value) {
            println("Name:\t$value")
            field = value
        }
    var phone = phone
        set(value) {
            println("Phone:\t$value")
            field = value
        }
    var budget = budget
        set(value) {
            println("Budget:\t$value")
            field = value
        }

    init {
        println("Name:\t$name")
        println("Phone:\t$phone")
        println("Budget:\t$budget")
    }

    fun saveMemento() : Memento {
        println("\nSaving state --\n")
        return Memento(name, phone, budget)
    }

    fun restoreMemento(memento: Memento) {
        println("\nRestoring state --")
        name = memento.name
        phone = memento.phone
        budget = memento.budget
    }
}

class Memento(val name: String, val phone: String, val budget: Number)

class ProspectMemory(val memento: Memento)