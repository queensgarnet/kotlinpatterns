fun main() {
    val color1 = Color.RED
    val color2 = Color.GREEN
    val color3 = Color.BLUE

    val y = Color.BLUE.cloneMe()
}

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0) {
        override fun cloneMe(): Color = RED.clone() as Color
    },
    GREEN(0, 255, 0) {
        override fun cloneMe(): Color = GREEN
    },
    BLUE(0, 0, 255) {
        override fun cloneMe(): Color = BLUE
    };

    abstract fun cloneMe(): Color
}
