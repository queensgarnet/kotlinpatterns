fun main() {
    val instance1 = Loadbalancer.instance()
    val instance2 = Loadbalancer.instance()
    val instance3 = Loadbalancer.instance()
    val instance4 = Loadbalancer.instance()

    assert(instance1 == instance2)
    assert(instance2 == instance3)
    assert(instance3 == instance4)

    val loadbalancer = Loadbalancer.instance()
    for (i in 1..20) {
        println("Dispatching request to: ${loadbalancer.nextServer()}")
    }
}

class Loadbalancer private constructor() {

    private val servers = listOf(
        Server("ServerI", "120.14.220.18"),
        Server("ServerII", "120.14.220.19"),
        Server("ServerIII", "120.14.220.20"),
        Server("ServerIV", "120.14.220.21"),
        Server("ServerV", "120.14.220.22"),
    )

    fun nextServer(): Server = servers.random()

    companion object {
        private val instance : Loadbalancer = Loadbalancer()
        fun instance() : Loadbalancer {
            return instance
        }
    }
}

data class Server(val name: String, val ip: String)