fun main() {
    val root = CompositeElement("Picture")
    root.add(PrimitiveElement("Red Line"))
    root.add(PrimitiveElement("Blue Circle"))
    root.add(PrimitiveElement("Green Box"))

    val comp = CompositeElement("Two Circles")
    comp.add(PrimitiveElement("Black Circle"))
    comp.add(PrimitiveElement("White Circle"))
    root.add(comp)

    val prim = PrimitiveElement("Yellow Line")
    root.add(prim)
    root.remove(prim)

    root.display(1)
}

class CompositeElement(name: String): DrawingElement(name) {
    private val elements = mutableListOf<DrawingElement>()

    override fun add(element: DrawingElement) {
        elements.add(element)
    }

    override fun remove(element: DrawingElement) {
        elements.remove(element)
    }

    override fun display(indent: Int) {
        println("-+".padStart(indent, '-').plus(" $name"))
        elements.forEach { it.display(indent + 2) }
    }
}

class PrimitiveElement(name: String): DrawingElement(name) {
    override fun add(element: DrawingElement) {
        throw Exception("Cannot add to a primitive element")
    }

    override fun remove(element: DrawingElement) {
        throw Exception("Cannot remove from a primitive element")
    }

    override fun display(indent: Int) {
        println("-".padStart(indent, '-').plus(" $name"))
    }
}

abstract class DrawingElement(protected val name: String) {
    abstract fun add(element: DrawingElement)
    abstract fun remove(element: DrawingElement)
    abstract fun display(indent: Int)
}