package store.view

import camp.nextstep.edu.missionutils.Console
import store.model.Cart
import store.model.Product
import store.model.Promotion
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.Locale

class InputView {
    fun readPromotions(): MutableList<Promotion> {
        val path = "src/main/resources/promotions.md"
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
        val path = "src/main/resources/products.md"
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
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        val s = Console.readLine()
        val regex = Regex("""^\[(.+?)-(\d+)](,\[(.+?)-(\d+)])*$""")
        require(regex.matches(s)) { "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요." }
        val file = s.split(",")
        val carts = mutableListOf<Cart>()
        file.forEach { text ->
            val cartInfo = text.split("-")
            carts.add(Cart(cartInfo[0],cartInfo[1].toIntOrNull() ?: 0))
        }
        return carts
    }
}