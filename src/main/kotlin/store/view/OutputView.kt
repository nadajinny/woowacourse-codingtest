package store.view

import store.model.stock.InStock

class OutputView {
    fun printError(e: String) {
        println("[ERROR] $e")
    }

    fun printWelcome() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.\n")
    }
}