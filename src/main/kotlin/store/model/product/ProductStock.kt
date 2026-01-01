package store.model.product

import store.model.promotion.Promotion

class ProductStock(
    private var quantity: Int,
    private val promotion: Promotion?
) {

    fun hasPromotion(): Boolean {
        return promotion != null
    }

    fun promotion(): Promotion? {
        return promotion
    }

    fun take(request: Int): Int {
        val taken = minOf(quantity, request)
        quantity -= taken
        return taken
    }

    fun remain(): Int {
        return quantity
    }
}