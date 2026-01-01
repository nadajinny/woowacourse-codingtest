package store.model.order

import store.model.promotion.Promotions
import store.model.product.Products
import store.view.InputView

class Order(
    private val items: List<OrderItem>
) {
    fun calculate(promotions: Promotions, products: Products, input: InputView): Receipt {
        val receipt = Receipt()
        items.forEach {
            val applied = promotions.apply(it, products, receipt)
            if (!applied) {
                receipt.addPurchase(it)
                products.deduct(it.product.name, it.quantity)
            }
        }
        receipt.applyMembership(input.readMembership())
        return receipt
    }
}
