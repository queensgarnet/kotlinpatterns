fun main() {
    val shop = Shop()

    var builder : VehicleBuilder = ScooterBuilder()
    shop.construct(builder)
    builder.vehicle.show()

    builder = CarBuilder()
    shop.construct(builder)
    builder.vehicle.show()
}

class Shop {
    fun construct(builder: VehicleBuilder) {
        builder.buildFrame()
        builder.buildEngine()
        builder.buildWheels()
        builder.buildDoors()
    }
}

abstract class VehicleBuilder(vehicleName: String) {
    val vehicle = Vehicle(vehicleName)

    abstract fun buildFrame()
    abstract fun buildEngine()
    abstract fun buildWheels()
    abstract fun buildDoors()
}

class ScooterBuilder() : VehicleBuilder("Scooter") {
    override fun buildFrame() {
        vehicle.setPart(Part.FRAME, "Scooter Frame")
    }

    override fun buildEngine() {
        vehicle.setPart(Part.ENGINE, "60 cc")
    }

    override fun buildWheels() {
        vehicle.setPart(Part.WHEELS, "2")
    }

    override fun buildDoors() {
        vehicle.setPart(Part.DOORS, "0")
    }
}

class CarBuilder() : VehicleBuilder("Car") {
    override fun buildFrame() {
        vehicle.setPart(Part.FRAME, "Car Frame")
    }

    override fun buildEngine() {
        vehicle.setPart(Part.ENGINE, "2500 cc")
    }

    override fun buildWheels() {
        vehicle.setPart(Part.WHEELS, "4")
    }

    override fun buildDoors() {
        vehicle.setPart(Part.DOORS, "4")
    }
}

enum class Part {
    FRAME,
    ENGINE,
    DOORS,
    WHEELS,
}

class Vehicle(private val vehicleType: String) {
    private val parts = mutableMapOf<Part, String>()

    fun setPart(part: Part, value: String) {
         parts[part] = value
    }

    fun show() {
        println("\n--------------------")
        println("Vehicle type: $vehicleType")
        println("Frame    : ${parts[Part.FRAME]}")
        println("Engine   : ${parts[Part.ENGINE]}")
        println("# Doors  : ${parts[Part.DOORS]}")
        println("# Wheels : ${parts[Part.WHEELS]}")
    }
}