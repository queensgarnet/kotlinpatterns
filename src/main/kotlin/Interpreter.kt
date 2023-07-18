fun main() {
    val roman = "MCMXXVIII"
    val context = Context(roman)

    val tree = listOf (
        ThousandExpression(),
        HundredExpression(),
        TenExpression(),
        OneExpression(),
    )

    tree.forEach { it.interpret(context) }

    println("$roman = ${context.output}")
}

class Context(var input: String) {
    var output = 0
}

abstract class Expression {
    fun interpret(context: Context) {
        if (context.input.isEmpty()) {
            return
        }

        if (context.input.startsWith(nine())) {
            context.output += 9 * multiplier()
            context.input = context.input.substring(2)
        }

        if (context.input.startsWith(four())) {
            context.output += 4 * multiplier()
            context.input = context.input.substring(2)
        }

        if (context.input.startsWith(five())) {
            context.output += 5 * multiplier()
            context.input = context.input.substring(1)
        }

        if (context.input.startsWith(one())) {
            context.output += 1 * multiplier()
            context.input = context.input.substring(1)
        }
    }

    abstract fun one(): String
    abstract fun four(): String
    abstract fun five(): String
    abstract fun nine(): String
    abstract fun multiplier(): Int
}

class ThousandExpression : Expression() {
    override fun one(): String = "M"
    override fun four(): String = " "
    override fun five(): String = " "
    override fun nine(): String = " "
    override fun multiplier(): Int = 1000
}

class HundredExpression : Expression() {
    override fun one(): String = "C"
    override fun four(): String = "CD"
    override fun five(): String = "D"
    override fun nine(): String = "CM"
    override fun multiplier(): Int = 100
}

class TenExpression : Expression() {
    override fun one(): String = "X"
    override fun four(): String = "XL"
    override fun five(): String = "L"
    override fun nine(): String = "XC"
    override fun multiplier(): Int = 10
}

class OneExpression : Expression() {
    override fun one(): String = "I"
    override fun four(): String = "IV"
    override fun five(): String = "V"
    override fun nine(): String = "IX"
    override fun multiplier(): Int = 1
}