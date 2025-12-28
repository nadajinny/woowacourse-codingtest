package christmas.model

import christmas.view.InputView
import christmas.view.OutputView
import java.awt.SystemColor.menu

class EventPlanner(
    public val date : Date,
    public val order : Order,
    private val output: OutputView
) {
    var christmasDiscount = 0
    var weekDiscount = 0
    var weekEndDiscount = 0
    var specialDiscount = 0
    fun checkGift () : Boolean {
        if(order.getTotalPrice() >= 120_000) return true
        return false
    }

    fun checkTotalBenefit() {
        var benefit = 0
        output.printOpeningBenefit()
        if(order.getTotalPrice()< 20_000) {
            output.printNone()
            return
        }
        if(date.isChristmas) {
            christmasDiscount = -1000 - 100*(date.date-1)
            output.printTotalBenefit("크리스마스 디데이 할인", christmasDiscount)
            benefit += christmasDiscount
        }
        if(date.isweek) {
            order.menus.forEach { (menu, i ) ->
                if(menu.category == "디저트") weekDiscount -= i*2_023
            }
            output.printTotalBenefit("평일 할인", weekDiscount)
            benefit += weekDiscount
        }

        if(date.isweekend) {
            order.menus.forEach { (menu, i) ->
                if(menu.category == "메인") weekEndDiscount -= i*2_023
            }
            output.printTotalBenefit("주말 할인", weekEndDiscount)
            benefit += weekEndDiscount
        }
        if(date.isspecial) {
            specialDiscount = -1000
            output.printTotalBenefit("특별 할인", specialDiscount)
            benefit += specialDiscount
        }
        if(checkGift()) {
            output.printTotalBenefit("증정 이벤트", -25_000)
            benefit -= 25_000
        }


        output.printTotalDiscount(benefit)
    }

    fun getTotalDiscount(): Int {
        return christmasDiscount + weekDiscount + weekEndDiscount + specialDiscount
    }
    fun getBadge(): String {
        var benefit = 0
        if(checkGift()) benefit += -25_000
        benefit += christmasDiscount + weekDiscount + weekEndDiscount + specialDiscount

        return when {
            benefit <= -20000 -> "산타"
            benefit <= -10000 -> "트리"
            benefit <= -5000 -> "별"
            else -> "없음"
        }

    }

}