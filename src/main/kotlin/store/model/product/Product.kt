package store.model.product

data class Product(
    val name: String,
    val price: Int
) {
    fun totalPrice(quantity: Int): Int {
        return price*quantity
    }
}