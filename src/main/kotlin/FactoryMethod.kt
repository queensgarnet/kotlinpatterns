fun main() {
    val documents = arrayListOf<Document>()

    documents.add(Resume())
    documents.add(Report())

    documents.forEach { document ->
        println("\n ${document::class.simpleName} --")
        document.pages.forEach { page ->
            println(" ${page::class.simpleName}")
        }
    }
}

abstract class Page {}

class SkillsPage : Page() {}
class EducationPage : Page() {}
class ExperiencePage : Page() {}
class IntroductionPage : Page() {}
class ResultsPage : Page() {}
class ConclusionPage : Page() {}
class SummaryPage : Page() {}
class BibliographyPage : Page() {}

abstract class Document {
    var pages = mutableListOf<Page>()
        private set

    init {
        createPages()
    }

    protected abstract fun createPages()
}

class Resume : Document() {
    override fun createPages() {
        pages.add(SkillsPage())
        pages.add(EducationPage())
        pages.add(ExperiencePage())
    }
}

class Report : Document() {
    override fun createPages() {
        pages.add(IntroductionPage())
        pages.add(ResultsPage())
        pages.add(ConclusionPage())
        pages.add(SummaryPage())
        pages.add(BibliographyPage())
    }
}