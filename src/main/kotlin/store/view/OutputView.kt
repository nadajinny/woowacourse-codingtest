package store.view

import store.model.Cart
import store.model.Product
import java.text.DecimalFormat

class OutputView {
    fun printError(e: String) {
        println("[ERROR] $e")
    }
    fun opening(products: List<Product>) {
        println("안녕하세요. W편의점입니다.\n" +
                "현재 보유하고 있는 상품입니다.")

        val nonPromoNames = products
            .filter { it.promotion == "null" }
            .map { it.name }
            .toSet()
        val printedNoStock = mutableSetOf<String>()

        products.forEach { product ->
            val promotion = promotionNull(product.promotion)
            val promotionSuffix = if (promotion.isBlank()) "" else " $promotion"
            println("- ${product.name} ${toDecimal(product.price)}원 ${quantityZero(product.quantity)}$promotionSuffix")

            if (product.promotion != "null" &&
                !nonPromoNames.contains(product.name) &&
                printedNoStock.add(product.name)
            ) {
                println("- ${product.name} ${toDecimal(product.price)}원 ${quantityZero(0)}")
            }
        }
        println()

    }


    fun checkProductPrice(name: String, products: List<Product>): Int {
        var price = 0
        products.forEach { product ->
            if(product.name==name) price = product.price
        }
        return price
    }

    fun printCart(carts: List<Cart>, products: List<Product>): Int {
        println("==============W 편의점================")
        println("상품명\t\t수량\t금액")
        var total = 0
        carts.forEach {
            println("${it.name}\t\t${it.quantity} \t${toDecimal(checkProductPrice(it.name, products)*it.quantity)}")
            total += it.quantity
        }
        return total
    }

    fun printGift(gift: List<Cart>) {
        println("=============증 정===============")
        gift.forEach {
            println("${it.name}\t\t${it.quantity}")
        }
    }

    fun printTotal(quantity: Int, total: Int, discount: Int, membership: Int) {
        println("====================================")
        println("총구매액\t\t${quantity}\t${toDecimal(total)}")
        println("행사할인\t\t\t-${toDecimal(discount)}")
        println("멤버십할인\t\t\t-${toDecimal(membership)}")
        println("내실돈\t\t\t ${toDecimal(total-discount-membership)}")
        println()
    }

    fun promotionNull(promotion: String): String {
        if(promotion=="null") return ""
        return promotion
    }
    fun quantityZero(i: Int): String {
        if(i == 0) return "재고 없음"
        return "${i}개"
    }

    fun toDecimal(i : Int): String {
        return DecimalFormat("#,###").format(i)
    }
}
