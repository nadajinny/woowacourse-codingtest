package store.model.stock

import store.model.promotion.Promotion
import store.model.promotion.PromotionType
import java.io.File
import java.text.DecimalFormat
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

    fun checkProduct(name: String): Product {
       return products.find { it.name == name} ?: throw IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요.")
    }

    fun showList() {
        products.forEach {
            when {
                it.promotionType!=null && it.promotionCount != 0 ->
                    println("- ${it.name} ${toDecimal(it.price)}원 ${it.promotionCount}개 ${it.promotionType!!.name}")
                it.promotionType!=null ->
                    println("- ${it.name} ${toDecimal(it.price)}원 재고 없음 ${it.promotionType!!.name}")
                it.promotionType==null && it.totalCount-it.promotionCount > 0  ->
                    println("- ${it.name} ${toDecimal(it.price)}원 ${it.totalCount-it.promotionCount}개")
                else ->
                    println("- ${it.name} ${toDecimal(it.price)}원 재고없음")
            }
        }
    }

    fun toDecimal(price : Int) : String {
        return DecimalFormat("#,###").format(price)
    }

}