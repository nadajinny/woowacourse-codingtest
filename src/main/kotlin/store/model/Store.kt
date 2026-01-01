package store.model

import store.model.promotion.Promotion
import store.model.stock.InStock
import store.view.InputView

class Store(
    val promotion: Promotion,
    val inStock: InStock,
    val input: InputView
) {
    fun shopping() {
        var cart = input.readCart()
        cart.checkQuantity(inStock)
    }
}