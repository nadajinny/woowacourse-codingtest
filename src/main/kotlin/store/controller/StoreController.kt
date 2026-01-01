package store.controller

import store.view.InputView
import store.view.OutputView
import store.model.product.Products
import store.model.promotion.Promotions

class StoreController {
    private val input = InputView()
    private val output = OutputView()
    private val products = Products.fromFile()
    private val promotions = Promotions.fromFile()
    fun run() {
        do {
            output.printWelcome(products)
            val order = input.readOrder(products)
            val receipt = order.calculate(promotions, products, input)
            output.printReceipt(receipt)
        }while(input.readContinue())
    }
}
