package store.view

import store.model.Cart
import store.model.Product
import store.model.Promotion
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.Locale

class InputView {
    fun readPromotions(): MutableList<Promotion> {
        val path = "../../resources/promotions.md"
        if(!File(path).exists()) IllegalArgumentException("[ERROR] 파일이 경로에 존재하지 않습니다.")
        val file = FileReader(path).readLines().drop(1)
        val promotions = mutableListOf<Promotion>()
        val DF = SimpleDateFormat("yyyy-MM-dd")
        file.forEach { text ->
            val promotionInfo = text.split(",")
            promotions.add(Promotion(
                promotionInfo[0],
                promotionInfo[1].toIntOrNull() ?: 0,
                promotionInfo[2].toIntOrNull() ?: 0,
                DF.parse(promotionInfo[3]),
                DF.parse(promotionInfo[4])
            ))
        }
        return promotions
    }

    fun readProducts(): MutableList<Product> {
        val path = "../../resources/products.md"
        if(!File(path).exists()) IllegalArgumentException("[ERROR] 파일이 경로에 존재하지 않습니다.")
        val file = FileReader(path).readLines().drop(1)
        val products = mutableListOf<Product>()
        file.forEach { text ->
            val productInfo = text.split(",")
            products.add(Product(
                productInfo[0],
                productInfo[1].toIntOrNull() ?: 0,
                productInfo[2].toIntOrNull() ?: 0,
                productInfo[3]
            ))
        }
        return products
    }

    fun readCarts(): MutableList<Cart> {

    }
}