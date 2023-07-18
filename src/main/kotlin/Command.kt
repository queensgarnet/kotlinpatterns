fun main() {
    val user = User()

    user.compute('+', 100)
    user.compute('-', 50)
    user.compute('*', 10)
    user.compute('/', 2)

    user.undo(4)

    user.redo(3)
}

class User {
    private val calculator = Calculator()
    private val commands = mutableListOf<Command>()
    private var current = 0

    fun compute(operator: Char, value: Int) {
        val command = CalculatorCommand(calculator, operator, value)
        command.execute()

        commands.add(command)
        current++
    }

    fun redo(levels: Int) {
        println("\n--- Redo $levels levels")
        for (i in 1..levels) {
            if (current < commands.count() - 1) {
                val command = commands[current++]
                command.execute()
            }
        }
    }

    fun undo(levels: Int) {
        println("\n--- Undo $levels levels")

        for (i in 1..levels) {
            if (current > 0) {
                val command = commands[--current]
                command.unExecute()
            }
        }
    }
}

class Calculator {
    private var current = 0

    fun operation(operator: Char, operand: Int) {
        when (operator) {
            '+' -> current += operand
            '-' -> current -= operand
            '*' -> current *= operand
            '/' -> current /= operand
            else -> throw Exception("Not a valid operation")
        }
        println("Current value = $current (Following $operator $operand)")
    }
}

abstract class Command {
    abstract fun execute()
    abstract fun unExecute()
}

class CalculatorCommand(
    private val calculator: Calculator,
    private val operator: Char,
    private val operand: Int
) : Command() {

    override fun execute() = calculator.operation(operator, operand)
    override fun unExecute() = calculator.operation(undo(operator), operand)

    private fun undo(operator: Char): Char {
        return when (operator) {
            '+' -> '-'
            '-' -> '+'
            '*' -> '/'
            '/' -> '*'
            else -> throw Exception("Invalid operator")
        }
    }
}