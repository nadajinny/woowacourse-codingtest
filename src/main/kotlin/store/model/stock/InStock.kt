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

            val products = mutableListOf<Product>()

            for(line in lines) {
                val (name, price, count, promoName) = line.split(",")
                val product = products.find{ it.name == name }

                if(product == null) {
                    products.add(
                        Product(
                            name,
                            price.toInt(),
                            count.toInt(),
                            if(promoName!="null") count.toInt() else 0,
                            promotion.find(promoName)
                        )
                    )
                } else {
                    product.totalCount += count.toInt()
                    if(promoName != "null") {
                        product.promotionCount += count.toInt()
                        product.promotionType = promotion.find(promoName)
                    }
                }
            }
            return InStock(products)
        }
    }

}