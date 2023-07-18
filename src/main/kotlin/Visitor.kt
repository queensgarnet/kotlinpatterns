import java.text.NumberFormat

fun main() {
    val employees = Employees()
    employees.attach(Clerk())
    employees.attach(Manager())
    employees.attach(ManagingDirector())

    employees.accept(IncomeVisitor())
    employees.accept(VacationVisitor())
}

interface IVisitor {
    fun visit(element: Element)
}

class IncomeVisitor : IVisitor {
    override fun visit(element: Element) {
        val employee = element as Employee
        employee.income *= 1.10

        println("${employee::class.simpleName} ${employee.name}'s new income: ${NumberFormat.getCurrencyInstance().format(employee.income) }")
    }
}

class VacationVisitor : IVisitor {
    override fun visit(element: Element) {
        val employee = element as Employee
        employee.vacationDays += 3

        println("${employee::class.simpleName} ${employee.name}'s new vacation days: ${employee.vacationDays}")
    }
}

class Employees {
    private val employees = mutableListOf<Employee>()

    fun attach(employee: Employee) {
        employees.add(employee)
    }

    fun detach(employee: Employee) {
        employees.remove(employee)
    }

    fun accept(visitor: IVisitor) {
        employees.forEach { it.accept(visitor) }
        println()
    }
}

abstract class Element {
    abstract fun accept(visitor: IVisitor)
}

open class Employee(val name: String, var income: Double, var vacationDays: Int) : Element() {
    override fun accept(visitor: IVisitor) {
        visitor.visit(this)
    }
}

class Clerk : Employee("Kevin", 25000.0, 14)
class Manager : Employee("Elly", 35000.0, 16)
class ManagingDirector : Employee("Eric", 45000.0, 21)
