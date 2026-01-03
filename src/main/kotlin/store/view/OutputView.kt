package store.view

import store.model.Product
import java.text.DecimalFormat

class OutputView {
    fun printError(e: String) {
        println("[ERROR] $e")
    }
    fun opening(products: List<Product>) {
        println("안녕하세요. W편의점입니다.\n" +
                "현재 보유하고 있는 상품입니다.")

        products.forEach { product ->
            println("- ${product.name} " +
                    "${toDecimal(product.price)}원 " +
                    "${quantityZero(product.quantity)} " +
                    "${promotionNull(product.promotion)}")
        }
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