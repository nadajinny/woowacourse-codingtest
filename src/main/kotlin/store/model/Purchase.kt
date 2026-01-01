package store.model

class Purchase(info : String) {
    val items = mutableMapOf<String, Int>()
    init {
        val box = info.split(",")
        box.forEach { s ->
            s.replace("[", "")
            s.replace("]", "")
            s.trim()
            val splitItem = s.split("-")
            items[splitItem[0]] = splitItem[1].toInt()
        }
    }
}