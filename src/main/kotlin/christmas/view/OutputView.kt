package christmas.view

import christmas.model.Menu

interface OutputView {
    fun printStartInformation() {}
    fun printRequestDate() {}
    fun printRequestOrder() {}
    fun printEventInstructions(date: Int) {}
    fun printStartPrintOrder() {}
    fun printOrder(menu: Menu, count: Int) {}
    fun printTotalPrice(totalPrice: Int) {}
    fun printGift(isGift : Boolean) {}
}