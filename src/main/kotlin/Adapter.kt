fun main() {
    val unknown = Compound()
    unknown.display()

    val water = RichCompound("Water")
    water.display()

    val benzene = RichCompound("Benzene")
    benzene.display()

    val ethanol = RichCompound("Ethanol")
    ethanol.display()
}

open class Compound {
    open fun display() {
        println("\nCompound: Unknown ----- ")
    }
}

class RichCompound(private val chemical: String): Compound() {
    override fun display() {
        val bank = ChemicalDatabank()

        println("\nCompound: $chemical ------")
        println("Formula: ${bank.getMolecularStructure(chemical)}")
        println("Weight: ${bank.getMolecularWeight(chemical)}")
        println("Melting Pt: ${bank.getCriticalPoint(chemical, "M")}")
        println("Boiling Pt: ${bank.getCriticalPoint(chemical, "B")}")
    }
}

class ChemicalDatabank {

    fun getCriticalPoint(compound: String, point: String): Number {
        return if (point == "M") {
            when (compound.lowercase()) {
                "water" -> 0.0
                "benzene" -> 5.5
                "ethanol" -> -144.1
                else -> 0
            }
        } else {
            when (compound.lowercase()) {
                "water" -> 100.0
                "benzene" -> 80.1
                "ethanol" -> 78.3
                else -> 0
            }
        }
    }

    fun getMolecularStructure(compound: String): String {
        return when (compound.lowercase()) {
            "water" -> "H20"
            "benzene" -> "C6H6"
            "ethanol" -> "C2H5OH"
            else -> ""
        }
    }

    fun getMolecularWeight(compound: String): Double {
        return when (compound.lowercase()) {
            "water" -> 18.015
            "benzene" -> 78.1134
            "ethanol" -> 46.0688
            else -> 0.0
        }
    }
}