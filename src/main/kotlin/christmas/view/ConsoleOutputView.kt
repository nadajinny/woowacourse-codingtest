package christmas.view

import christmas.util.OutputMessages
import java.text.DecimalFormat

class ConsoleOutputView: OutputView {
    override fun printOpening(message: String) {
        println(message)
    }

    override fun nextLine() {
        println()
    }

    override fun printError(e: String) {
        println(e)
    }


    override fun printOpeningEvent(i: Int) {
        println("12월 ${i}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    override fun printEachMenu(menu: String, count: Int) {
        println("${menu} ${count}개")
    }

    override fun printPrice(price: Int) {
        println(DecimalFormat("#,###").format(price)+"원")
    }

    override fun printSpecificBenefit(i: String, price: Int) {
        println("${i}: -${DecimalFormat("#,###".format(price))}원")
    }
}