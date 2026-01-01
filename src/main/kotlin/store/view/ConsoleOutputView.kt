package store.view

import store.model.Convenience
import java.text.DecimalFormat

class ConsoleOutputView: OutputView {
    override fun opening() {
        println("안녕하세요. W편의점입니다.")
    }
    override fun opening_show_inventory() {
        println("현재 보유하고 있는 상품입니다.")
        println()
    }

    override fun show_inventory(name: String, price: Int, quantity: Int, promotion: String) {
        var promotion = promotion
        if (promotion == "null") promotion = ""
        var quantity = quantity.toString() + "개"
        if(quantity == "0개") quantity = "재고 없음"
        println("${name} ${DecimalFormat("#,###").format(price)} ${quantity} ${promotion}")
    }

    override fun purchase_guide() {
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
    }

    override fun next_line() {
        println()
    }

    override fun Error(e: String) {
        println(e)
    }
}