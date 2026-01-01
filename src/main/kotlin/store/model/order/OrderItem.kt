package store.model.order

import store.model.product.Product

data class OrderItem(
    val product: Product,
    val quantity: Int
) {
    fun price(): Int {
        return product.totalPrice(quantity)
    }
}