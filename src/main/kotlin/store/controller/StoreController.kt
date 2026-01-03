package store.controller

import store.model.Store
import store.view.InputView
import store.view.OutputView

class StoreController {
    private val input = InputView()
    private val output = OutputView()
    fun run() {
        val store = Store(input.readPromotions(), input.readProducts())
        while(true) {
            output.opening(store.products)
            try {
                store.carts = input.readCarts()
                store.checkCart() //Cart에서 점검해야할 요소 -> 상품에 있는지, 재고가 존재하는지

            }catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }

    }
}