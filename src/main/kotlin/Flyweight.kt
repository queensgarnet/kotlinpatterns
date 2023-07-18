fun main() {
    val document = "AAZZBBZB"
    val chars = document.toCharArray()

    val factory = CharacterFactory()

    var pointSize = 10

    chars.forEach {
        pointSize++
        val character = factory.getCharacter(it)
        character.display(pointSize)
    }
}

class CharacterFactory {
    private val characters = mutableMapOf<Char, Character>()

    fun getCharacter(key: Char) : Character {
        if (characters.containsKey(key)) {
            return characters[key]!!
        }
        val character = when (key) {
            'A' -> CharacterA()
            'B' -> CharacterB()
            'Z' -> CharacterZ()
            else -> throw Exception("Unknown character")
        }
        characters[key] = character
        return character
    }
}

abstract class Character {
    abstract val symbol: Char
    abstract val width: Int
    abstract val height: Int
    abstract val ascent: Int
    abstract val descent: Int
    private var pointSize = 0

    fun display(pointSize: Int) {
        this.pointSize = pointSize
        println("$symbol (point size $pointSize)")
    }
}

class CharacterA: Character() {
    override val symbol = 'A'
    override val width = 100
    override val height = 120
    override val ascent = 70
    override val descent = 0
}

class CharacterB: Character() {
    override val symbol = 'B'
    override val width = 100
    override val height = 140
    override val ascent = 72
    override val descent = 0
}

class CharacterZ: Character() {
    override val symbol = 'Z'
    override val width = 100
    override val height = 100
    override val ascent = 68
    override val descent = 0
}