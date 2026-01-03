package store.model

import camp.nextstep.edu.missionutils.DateTimes
import store.view.InputView
import store.view.OutputView

class Store(
    val promotions: MutableList<Promotion>,
    val products: MutableList<Product>,
    val input: InputView,
    val output: OutputView
) {
    var carts = mutableListOf<Cart>()
    var gift = mutableListOf<Cart>()
    val now = DateTimes.now().toLocalDate()
    var isMembership = false

    /* ---------- 검증 ---------- */

    fun checkCart() {
        carts.forEach { cart ->
            require(products.any { it.name == cart.name }) {
                "존재하지 않는 상품입니다. 다시 입력해 주세요."
            }
            require(totalStock(cart.name) >= cart.quantity) {
                "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."
            }
        }
    }

    fun totalStock(name: String): Int {
        return products.filter { it.name == name }.sumOf { it.quantity }
    }

    /* ---------- 프로모션 적용 ---------- */

    fun addOrRemoveCart() {
        gift.clear()
        carts.forEach { cart ->
            val promoProduct = promotionProduct(cart.name) ?: return@forEach
            val promotion = findPromotion(promoProduct.promotion)

            if (now.isBefore(promotion.startDate) || now.isAfter(promotion.endDate)) {
                return@forEach
            }

            val unit = promotion.buy + promotion.get
            val maxPromoSet = promoProduct.quantity / unit
            val cartSet = cart.quantity / unit

            // 🎁 추가 증정 가능
            if (cart.quantity < promoProduct.quantity &&
                cart.quantity % unit == promotion.buy
            ) {
                if (askAddCart(cart.name)) {
                    cart.quantity++
                }
            }

            val appliedSet = minOf(cart.quantity / unit, maxPromoSet)
            if (appliedSet > 0) {
                gift.add(Cart(cart.name, appliedSet))
            }

            // ❌ 일부만 적용
            val promoAppliedQty = appliedSet * unit
            val nonPromoQty = cart.quantity - promoAppliedQty

            if (nonPromoQty > 0) {
                if (askRemoveCart(cart.name, nonPromoQty)) {
                    cart.quantity -= nonPromoQty
                }
            }
        }
    }

    /* ---------- 재고 차감 ---------- */

    fun applyStock() {
        carts.forEach { cart ->
            var remain = cart.quantity

            products
                .filter { it.name == cart.name }
                .forEach { product ->
                    val used = minOf(product.quantity, remain)
                    product.quantity -= used
                    remain -= used
                }
        }
    }

    /* ---------- 출력 ---------- */

    fun printReceipt() {
        val quantity = output.printCart(carts, products)
        output.printGift(gift)

        val total = calculateTotal()
        val discount = calculateDiscount()
        val membership = calculateMembershipDiscount(total, discount)

        output.printTotal(quantity, total, discount, membership)
    }

    /* ---------- 금액 계산 ---------- */

    fun calculateTotal(): Int {
        return carts.sumOf { it.quantity * productPrice(it.name) }
    }

    fun calculateDiscount(): Int {
        return gift.sumOf { it.quantity * productPrice(it.name) }
    }

    fun calculateMembershipDiscount(total: Int, discount: Int): Int {
        if (!isMembership) return 0
        val eligibleAmount = total - discount
        if (eligibleAmount <= 0) return 0
        val value = (eligibleAmount * 0.3).toInt()
        return minOf(value, 8_000)
    }

    /* ---------- 조회 ---------- */

    fun productPrice(name: String): Int {
        return products.first { it.name == name }.price
    }

    fun promotionProduct(name: String): Product? {
        return products.find { it.name == name && it.promotion != "null" }
    }

    fun findPromotion(name: String): Promotion {
        return promotions.firstOrNull { it.name == name }
            ?: throw IllegalArgumentException("[ERROR] 프로모션 정보가 없습니다.")
    }

    /* ---------- 입력 ---------- */

    fun askAddCart(name: String): Boolean {
        while (true) {
            try {
                return input.askAddCart(name)
            } catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }
    }

    fun askRemoveCart(name: String, minus: Int): Boolean {
        while (true) {
            try {
                return input.askRemoveCart(name, minus)
            } catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }
    }

    fun askMembership() {
        while (true) {
            try {
                isMembership = input.askMembership()
                return
            } catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }
    }
}
