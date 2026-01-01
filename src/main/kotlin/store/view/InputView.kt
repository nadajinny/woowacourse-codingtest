package store.view

import camp.nextstep.edu.missionutils.Console
import store.model.order.Order
import store.model.product.Products
import store.util.Parser

class InputView {
    fun readOrder(products: Products): Order {
        try {
            println("구매하실 상품명과 수량을 입력해 주세요. (예: [콜라-10],[사이다-3])")
            val input = Console.readLine()
            return Parser.parseOrder(input, products)
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
            return readOrder(products)
        }
    }

    fun readMembership(): Boolean {
        println("멤버십 할인을 받으시겠습니까? (Y/N)")
        return Console.readLine() == "Y"
    }

    fun readContinue(): Boolean {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        return Console.readLine() == "Y"
    }
}
