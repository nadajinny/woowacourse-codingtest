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


    }
}