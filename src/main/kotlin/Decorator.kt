fun main() {
    val book = Book("Uncle Bob", "Clean Code", 10)
    book.display()

    val video = Video("Spielberg", "Jaws", 92, 23)
    video.display()

    println("\nMaking video borrowable:")

    val borrowVideo = Borrowable(video)
    borrowVideo.borrowItem("Customer #1")
    borrowVideo.borrowItem("Customer #2")

    borrowVideo.display()

}

abstract class LibraryItem(var numCopies: Int) {
    abstract fun display()
}

class Book(private val author: String, private val title: String, copies: Int) : LibraryItem(copies) {
    override fun display() {
        println("\nBook ------")
        println(" Author: $author")
        println(" Title: $title")
        println(" # Copies: $numCopies")
    }
}

class Video(
    private val director: String,
    private val title: String,
    private val playtime: Int,
    copies: Int
) : LibraryItem(copies) {

    override fun display() {
        println("\nVideo ------")
        println(" Director: $director")
        println(" Title: $title")
        println(" # Copies: $numCopies")
        println(" Playtime: $playtime")
    }
}

abstract class Decorator(protected val libraryItem: LibraryItem) : LibraryItem(libraryItem.numCopies) {
    override fun display() {
        libraryItem.display()
    }
}

class Borrowable(libraryItem: LibraryItem) : Decorator(libraryItem) {
    private val borrowers = mutableListOf<String>()

    fun borrowItem(name: String) {
        borrowers.add(name)
        libraryItem.numCopies--
    }

    fun returnItem(name: String) {
        borrowers.remove(name)
        libraryItem.numCopies++
    }

    override fun display() {
        super.display()

        borrowers.forEach { println(" borrower: $it") }
    }
}