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
            if (promotion == "null") promotion = "재고없음"
            println("${it.name} ${DecimalFormat("#,###").format(it.price)} ${it.quantity} ${promotion}")
        }
        println()
    }
}