package store.view

import store.model.Convenience
import java.text.DecimalFormat

class ConsoleOutputView: OutputView {
    override fun opening() {
        println("안녕하세요. W편의점입니다.")
    }

    override fun show_inventory(convenience: Convenience) {
        println("현재 보유하고 있는 상품입니다.")
        println()
        convenience.inventory.forEach { it ->
            var promotion = it.promotion
            if (promotion == "null") promotion = ""
            var quantity = it.quantity.toString() + "개"
            if(it.quantity == 0) quantity = "재고 없음"
            println("${it.name} ${DecimalFormat("#,###").format(it.price)} ${quantity} ${promotion}")
        }
        println()
    }

    override fun purchase_guide() {
        println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")
    }

    override fun next_line() {
        println()
    }
}