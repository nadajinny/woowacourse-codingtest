package store.model.stock

import store.model.promotion.Promotion
import store.model.promotion.PromotionType
import java.io.File
import java.time.LocalDate

class InStock(
    var products: List<Product>
) {
    companion object {
        fun fromFile(promotion: Promotion): InStock {
            val lines = File("src/main/resources/products.md")
                .readLines()
                .drop(1)

            val map = lines.forEach {
                val (name, price, count, promotion) = it.split(",")
            }
            return InStock(map)
        }
    }

}