package store.controller

import store.model.Store
import store.model.promotion.Promotion
import store.model.stock.InStock
import store.view.InputView
import store.view.OutputView

class StoreController {
    private val input = InputView()
    private val output = OutputView()
    private val promotion = Promotion.fromFile()
    private val inStock = InStock.fromFile(promotion)
    fun run() {
        do {
            try{
                output.printWelcome()
                inStock.showList()
                val store = Store(promotion, inStock,input)
                store.shopping()
            }catch(e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }while(input.readContinue())
    }
}