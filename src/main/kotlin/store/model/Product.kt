package store.model

class Product(info: String) {
    val name : String
    val price : Int
    val quantity : Int
    val promotion : String
    init {
        val splitedInfo = info.split(",")
        name = splitedInfo[0]
        require(!name.trim().isEmpty()) {"문자열이 비어있음"}
        price = splitedInfo[1].toIntOrNull() ?: 0
        quantity = splitedInfo[2].toIntOrNull() ?: 0
        promotion = splitedInfo[3]
        require(!name.trim().isEmpty()) {"문자열이 비어있음"}
    }

}