import java.math.BigDecimal

fun main() {
    val proxy = MathProxy()

    println("4 + 2 = ${proxy.add(4, 2)}")
    println("4 - 2 = ${proxy.sub(4, 2)}")
    println("4 * 2 = ${proxy.mul(4, 2)}")
    println("4 / 3 = ${proxy.div(4, 3)}")
}

interface IMath {
    fun add(x: Int, y: Int): Number
    fun sub(x: Int, y: Int): Number
    fun mul(x: Int, y: Int): Number
    fun div(x: Int, y: Int): Number
}

class Math : IMath {
    override fun add(x: Int, y: Int): Number = x + y
    override fun sub(x: Int, y: Int): Number = x - y
    override fun mul(x: Int, y: Int): Number = x * y
    override fun div(x: Int, y: Int): Number = x.toDouble() / y.toDouble()
}

class MathProxy : IMath {

    private val math = Math()

    override fun add(x: Int, y: Int): Number = math.add(x, y)
    override fun sub(x: Int, y: Int): Number = math.sub(x, y)
    override fun mul(x: Int, y: Int): Number = math.mul(x, y)
    override fun div(x: Int, y: Int): Number = math.div(x, y)
}