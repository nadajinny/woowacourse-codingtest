package store.model.order

import store.model.discount.MembershipDiscount
import store.model.product.Product

data class PurchaseLine(
    val name: String,
    val quantity: Int,
    val price: Int
)

data class GiftLine(
    val name: String,
    val quantity: Int
)

class Receipt {
    private var total = 0
    private var promotionDiscount = 0
    private var membershipDiscount = 0
    private var totalQuantity = 0
    private val purchases = linkedMapOf<String, PurchaseLine>()
    private val gifts = linkedMapOf<String, Int>()

    fun addPurchase(item: OrderItem) {
        addPurchase(item.product, item.quantity)
    }

    fun addPurchase(product: Product, quantity: Int) {
        if (quantity <= 0) return
        total += product.totalPrice(quantity)
        totalQuantity += quantity

        val existing = purchases[product.name]
        val linePrice = product.totalPrice(quantity)
        purchases[product.name] = if (existing == null) {
            PurchaseLine(product.name, quantity, linePrice)
        } else {
            PurchaseLine(
                existing.name,
                existing.quantity + quantity,
                existing.price + linePrice
            )
        }
    }

    fun addGift(name: String, quantity: Int) {
        if (quantity <= 0) return
        gifts[name] = (gifts[name] ?: 0) + quantity
    }

    fun addPromotionDiscount(amount: Int) {
        promotionDiscount += amount
    }

    fun applyMembership(enabled: Boolean) {
        if (!enabled) return
        val discount = MembershipDiscount().calculate(total - promotionDiscount)
        membershipDiscount = discount
    }

    fun purchases(): List<PurchaseLine> {
        return purchases.values.toList()
    }

    fun gifts(): List<GiftLine> {
        return gifts.map { (name, quantity) -> GiftLine(name, quantity) }
    }

    fun totalQuantity(): Int {
        return totalQuantity
    }

    fun totalPrice(): Int {
        return total
    }

    fun promotionDiscount(): Int {
        return promotionDiscount
    }

    fun membershipDiscount(): Int {
        return membershipDiscount
    }

    fun finalPrice(): Int {
        return total - promotionDiscount - membershipDiscount
    }
}
