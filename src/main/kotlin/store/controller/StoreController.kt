package store.controller

import store.model.Store
import store.view.InputView
import store.view.OutputView

class StoreController {
    private val input = InputView()
    private val output = OutputView()
    fun run() {
        val store = Store(input.readPromotions(), input.readProducts(), input, output)
        do{
            output.opening(store.products)
            while (true) {
                try {
                    store.carts = input.readCarts()
                    store.checkCart() //Cart에서 점검해야할 요소 -> 상품에 있는지, 재고가 존재하는지
                    break
                } catch (e: IllegalArgumentException) {
                    output.printError(e.message!!)
                }
            }

            store.addOrRemoveCart() //Cart에서 혜택받는 요소와 못받는 요소 점검
            store.askMembership()
            store.printReceipt()
        }while(askClosingSafely())

    }

    private fun askClosingSafely(): Boolean {
        while (true) {
            try {
                return input.closing()
            } catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }
    }
}
