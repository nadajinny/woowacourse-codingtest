package store.model.promotion


import camp.nextstep.edu.missionutils.Console
import store.model.order.OrderItem
import store.model.order.Receipt
import store.model.product.Products


class Promotions(
    private val promotionRepository: PromotionRepository
) {

    companion object {
        fun fromFile(): Promotions {
            return Promotions(PromotionRepository.fromFile())
        }
    }

    fun apply(
        item: OrderItem,
        products: Products,
        receipt: Receipt
    ): Boolean {
        val stocks = products.stocksOf(item.product.name)
        val promoStock = stocks.firstOrNull { it.hasPromotion() } ?: return false
        val promotion = promoStock.promotion() ?: return false
        if (!promotion.isActive()) return false

        var quantity = item.quantity
        quantity += handleLessQuantity(item, promotion, products)

        val promoCount = products.promoStockCount(item.product.name)
        val nonPromoCount = products.nonPromoStockCount(item.product.name)

        var plan = buildPlan(quantity, promoCount, nonPromoCount, promotion)
        if (plan.shortageFree > 0) {
            val keepBuying = handlePromotionStockShortage(item, plan.shortageFree)
            if (!keepBuying) {
                quantity = adjustQuantity(quantity, promoCount, nonPromoCount, promotion)
                plan = buildPlan(quantity, promoCount, nonPromoCount, promotion)
            }
        }

        if (quantity > 0) {
            receipt.addPurchase(item.product, quantity)
        }
        if (plan.freeQuantity > 0) {
            receipt.addGift(item.product.name, plan.freeQuantity)
            receipt.addPromotionDiscount(plan.freeQuantity * item.product.price)
        }
        products.deductByType(item.product.name, plan.promoDeduct, plan.nonPromoDeduct)
        return true
    }

    private fun handleLessQuantity(
        item: OrderItem,
        promotion: Promotion,
        products: Products
    ): Int {
        val needed = promotion.additionalNeeded(item.quantity)
        if (needed <= 0) return 0
        if (item.quantity + needed > products.totalStockCount(item.product.name)) return 0

        println("현재 ${item.product.name}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)")
        return if (Console.readLine() == "Y") needed else 0
    }

    private fun handlePromotionStockShortage(item: OrderItem, shortage: Int): Boolean {
        println("현재 ${item.product.name} ${shortage}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)")
        return Console.readLine() == "Y"
    }

    private data class PromotionPlan(
        val purchaseQuantity: Int,
        val freeQuantity: Int,
        val promoDeduct: Int,
        val nonPromoDeduct: Int,
        val shortageFree: Int
    )

    private fun buildPlan(
        quantity: Int,
        promoStock: Int,
        nonPromoStock: Int,
        promotion: Promotion
    ): PromotionPlan {
        if (quantity <= 0) return PromotionPlan(0, 0, 0, 0, 0)

        val expectedFree = promotion.freeQuantity(quantity)
        val maxSetsByQuantity = quantity / promotion.buy
        val maxSetsByPromo = promoStock / (promotion.buy + promotion.get)
        val extraStock = promoStock + nonPromoStock - quantity
        val maxSetsByExtra = if (extraStock > 0) extraStock / promotion.get else 0
        val sets = minOf(maxSetsByQuantity, maxSetsByPromo, maxSetsByExtra)

        val free = sets * promotion.get
        val promoPurchase = sets * promotion.buy
        val promoUsedForSets = sets * (promotion.buy + promotion.get)
        val remainingPurchases = quantity - promoPurchase
        val promoRemaining = promoStock - promoUsedForSets
        val promoNoDiscount = minOf(remainingPurchases, promoRemaining)
        val nonPromoNoDiscount = remainingPurchases - promoNoDiscount
        val shortage = expectedFree - free

        return PromotionPlan(
            purchaseQuantity = quantity,
            freeQuantity = free,
            promoDeduct = promoUsedForSets + promoNoDiscount,
            nonPromoDeduct = nonPromoNoDiscount,
            shortageFree = shortage
        )
    }

    private fun adjustQuantity(
        quantity: Int,
        promoStock: Int,
        nonPromoStock: Int,
        promotion: Promotion
    ): Int {
        var adjusted = quantity
        while (adjusted > 0) {
            val plan = buildPlan(adjusted, promoStock, nonPromoStock, promotion)
            if (plan.shortageFree <= 0) return adjusted
            adjusted -= 1
        }
        return 0
    }
}
