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
    fun printOpeningBenefit() {}
    fun printTotalBenefit(which: String, price: Int) {}
    fun printNone() {}
    fun printTotalDiscount(price : Int) {}
    fun printAfterDiscountPrice(totalPrice: Int, discount: Int)
    fun printBadget(badge: String)
}