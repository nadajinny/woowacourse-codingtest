package store.contoller

import store.model.Convenience
import store.model.Product
import store.model.Purchase
import store.view.InputView
import store.view.OutputView

class ConvenienceController(
    private val input: InputView,
    private val output: OutputView
) {
    fun run() {
        val inputProduct = input.readProduct()
        val inputPromotion = input.readPromotion()

        val convenience = Convenience(inputProduct, inputPromotion)
        start_convenience(convenience)
    }

    private fun start_convenience(convenience: Convenience) {
        output.opening()
        show_inventory(convenience)
        val purchase = parse_purchase()
    }

    private fun parse_purchase() : MutableList<Purchase>  {
        output.purchase_guide()
        val purchaseInput = input.readLine()
        val purchaseBox = mutableListOf<Purchase>()
        output.next_line()
        return purchaseBox
    }

    private fun show_inventory(convenience: Convenience) {
        output.show_inventory(convenience)
    }
}