package store.contoller

import store.model.Convenience
import store.model.Product
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
    }

    private fun show_inventory(convenience: Convenience) {
        output.show_inventory(convenience)
    }
}