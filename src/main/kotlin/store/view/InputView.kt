package store.view

import camp.nextstep.edu.missionutils.Console
import store.model.cart.Cart
import store.util.Parser
import store.util.Validator
import java.util.IllformedLocaleException

class InputView {
    fun readPromotion() {

    }

    fun readContinue(): Boolean {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        return Console.readLine() == "Y"
    }

    fun readCart(): Cart {
        println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
        val input = Console.readLine() ?: ""
        return Parser().parseCart(input)
    }
}