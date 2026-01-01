package store.model.product

import store.model.order.OrderItem
import store.model.promotion.PromotionRepository
import java.io.File

class Products(
    private val products: Map<String, Product>,
    private val stocks: Map<String, List<ProductStock>>
) {

    companion object {
        fun fromFile(): Products {
            val promotionRepo = PromotionRepository.fromFile()

            val lines = File("src/main/resources/products.md")
                .readLines()
                .drop(1) // header 제거

            val productMap = mutableMapOf<String, Product>()
            val stockMap = mutableMapOf<String, MutableList<ProductStock>>()

            lines.forEach {
                val (name, price, qty, promoName) = it.split(",")

                productMap.putIfAbsent(name, Product(name, price.toInt()))

                val promotion = promotionRepo.find(promoName)
                stockMap.computeIfAbsent(name) { mutableListOf() }
                    .add(ProductStock(qty.toInt(), promotion))
            }

            return Products(productMap, stockMap)
        }
    }

    fun validateStock(name: String, quantity: Int) {
        val total = stocks[name]?.sumOf { it.remain() } ?: 0
        if (quantity > total) {
            throw IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.")
        }
    }

    fun createOrderItem(name: String, quantity: Int): OrderItem {
        if (quantity <= 0) {
            throw IllegalArgumentException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.")
        }
        val product = product(name)
        validateStock(name, quantity)
        return OrderItem(product, quantity)
    }

    fun stocksOf(name: String): List<ProductStock> {
        product(name)
        return stocks[name]?.toList() ?: emptyList()
    }

    fun promoStockCount(name: String): Int {
        return stocks[name]?.filter { it.hasPromotion() }?.sumOf { it.remain() } ?: 0
    }

    fun nonPromoStockCount(name: String): Int {
        return stocks[name]?.filter { !it.hasPromotion() }?.sumOf { it.remain() } ?: 0
    }

    fun totalStockCount(name: String): Int {
        return promoStockCount(name) + nonPromoStockCount(name)
    }

    fun deduct(name: String, quantity: Int) {
        var remain = quantity

        stocks[name]!!.filter { it.hasPromotion() }.forEach {
            remain -= it.take(remain)
        }
        stocks[name]!!.filter { !it.hasPromotion() }.forEach {
            remain -= it.take(remain)
        }
    }

    fun deductByType(name: String, promoQuantity: Int, nonPromoQuantity: Int) {
        var promoRemain = promoQuantity
        stocks[name]!!.filter { it.hasPromotion() }.forEach {
            promoRemain -= it.take(promoRemain)
        }

        var nonPromoRemain = nonPromoQuantity
        stocks[name]!!.filter { !it.hasPromotion() }.forEach {
            nonPromoRemain -= it.take(nonPromoRemain)
        }
    }

    fun product(name: String): Product {
        return products[name]
            ?: throw IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요.")
    }

    fun print() {
        products.forEach { (name, product) ->
            val stockList = stocks[name].orEmpty()
            var hasPromo = false
            var hasNonPromo = false

            stockList.forEach { stock ->
                if (stock.hasPromotion()) hasPromo = true else hasNonPromo = true
                println(formatStockLine(name, product.price, stock))
            }

            if (hasPromo && !hasNonPromo) {
                println(formatEmptyNonPromoLine(name, product.price))
            }
        }
    }

    private fun formatStockLine(name: String, price: Int, stock: ProductStock): String {
        val priceText = formatMoney(price)
        val quantityText = if (stock.remain() == 0) "재고 없음" else "${stock.remain()}개"
        val promoName = stock.promotion()?.name
        return if (promoName.isNullOrBlank()) {
            "- $name ${priceText}원 $quantityText"
        } else {
            "- $name ${priceText}원 $quantityText $promoName"
        }
    }

    private fun formatEmptyNonPromoLine(name: String, price: Int): String {
        val priceText = formatMoney(price)
        return "- $name ${priceText}원 재고 없음"
    }

    private fun formatMoney(amount: Int): String {
        return String.format("%,d", amount)
    }
}
