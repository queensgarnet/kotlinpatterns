fun main () {
    val africa = AfricaFactory()
    var world = AnimalWorld(africa)
    world.runFoodChain()

    val america = AmericaFactory()
    world =  AnimalWorld(america)
    world.runFoodChain()
}


class AnimalWorld(factory: ContinentFactory) {
    private val herbivore = factory.createHerbivore()
    private val carnivore = factory.createCarnivore()

    fun runFoodChain() {
        carnivore.eats(herbivore)
    }
}

class AfricaFactory : ContinentFactory() {
    override fun createHerbivore(): Herbivore {
        return Zebra()
    }

    override fun createCarnivore(): Carnivore {
        return Lion()
    }

}

class AmericaFactory : ContinentFactory() {
    override fun createHerbivore(): Herbivore {
        return Bison()
    }

    override fun createCarnivore(): Carnivore {
        return Wolf()
    }

}

abstract class ContinentFactory {
    abstract fun createHerbivore() : Herbivore
    abstract fun createCarnivore() : Carnivore
}

abstract class Carnivore() {
    fun eats(herbivore: Herbivore) {
        println("${this::class.simpleName} eats ${herbivore::class.simpleName}")
    }
}

abstract class Herbivore() {}

class Lion : Carnivore() {}
class Zebra : Herbivore() {}
class Wolf : Carnivore() {}
class Bison : Herbivore() {}
