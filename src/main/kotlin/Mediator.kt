fun main() {
    val chatroom = Chatroom()

    val george = Beatle("George")
    val paul = Beatle("Paul")
    val ringo = Beatle("Ringo")
    val john = Beatle("John")
    val yoko = NonBeatle("Yoko")

    chatroom.register(george)
    chatroom.register(paul)
    chatroom.register(ringo)
    chatroom.register(john)
    chatroom.register(yoko)

    yoko.send("John", "Hi John!")
    paul.send("Ringo", "All you need is love")
    ringo.send("George", "My sweet lord!")
    paul.send("John", "Can't buy me love")
    john.send("Yoko", "My sweet love")
}
abstract class AbstractChatroom {
    abstract fun register(participant : Participant)
    abstract fun send(from: String, to: String, message: String)
}

class Chatroom : AbstractChatroom() {
    private val participants = mutableMapOf<String, Participant>()
    override fun register(participant: Participant) {
        if (!participants.containsValue(participant)) {
            participants[participant.name] = participant
        }
        participant.chatroom = this
    }

    override fun send(from: String, to: String, message: String) {
        val participant = participants[to]
        participant?.receive(from, message)
    }
}

open class Participant(val name: String) {
    var chatroom: Chatroom? = null

    fun send(to: String, message: String) {
        chatroom?.send(name, to, message)
    }

    open fun receive(from: String, message: String) {
        println("$from to $name: '$message'")
    }
}

class Beatle(name: String) : Participant(name) {
    override fun receive(from: String, message: String) {
        print("To a Beatle: ")
        super.receive(from, message)
    }
}

class NonBeatle(name: String) : Participant(name) {
    override fun receive(from: String, message: String) {
        print("To a non-Beatle: ")
        super.receive(from, message)
    }
}