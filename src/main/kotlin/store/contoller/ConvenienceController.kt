package store.contoller

import store.model.Convenience
import store.model.Purchase
import store.view.InputView
import store.view.OutputView
import camp.nextstep.edu.missionutils.DateTimes
import java.time.chrono.ChronoLocalDateTime

class ConvenienceController(
    private val input: InputView,
    private val output: OutputView
) {
    fun run() {
        val inputProduct = input.readProduct()
        val inputPromotion = input.readPromotion()
        output.opening()
        val convenience = Convenience(inputProduct, inputPromotion, output)
        while(true) {
            try{
                openConvenience(convenience)
            }catch (e: IllegalArgumentException) {
                output.Error(e.message!!)
            }
        }
    }
    private fun openConvenience(convenience: Convenience) {
        parse_purchase(convenience)
        convenience.validate_purchase()
    }
    private fun parse_purchase(convenience: Convenience)  {
        output.purchase_guide()
        val purchaseInput = input.readLine()
        val purchaseBox = convenience.checkPurchaseList(purchaseInput)
        output.next_line()
    }



}