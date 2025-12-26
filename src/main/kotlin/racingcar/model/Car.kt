package racingcar.model

class Car(private val name: String) {
    private var distance = 0

    init {
        if(name.isBlank()) throw IllegalArgumentException()
        if(name.length > 5) throw IllegalArgumentException()
    }

    fun add() {
        distance++
    }

    fun getDistance(): Int {
        return distance
    }

    fun getName(): String {
        return name
    }
}