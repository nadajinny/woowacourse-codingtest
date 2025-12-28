package christmas.controller

import christmas.model.Date
import christmas.model.EventPlanner
import christmas.model.Order
import christmas.util.ErrorMessages
import christmas.util.OutputMessages
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input : InputView,
    private val output : OutputView
) {
    fun run() {
        output.printOpening(OutputMessages.PROMPT_OPENING)
        val date = inputDate()
        val order = inputOrder()
        val planner = EventPlanner(date, order)
        showEvent(date, order, planner)
    }

    private fun showEvent(date: Date, order: Order, planner: EventPlanner) {
        output.printOpeningEvent(date.getDate())
        output.nextLine()
        MenuList(order)
        val beforediscount = beforeDiscount(order)
        output.nextLine()
        GiftMenu(planner)
        output.nextLine()
        val (discount, benefit) = BenefitList(planner)
        output.nextLine()
        BenefitPrice(benefit)
        output.nextLine()
        ExpectedPrice(beforediscount, discount)
        output.nextLine()
        EventBadge(benefit, planner)
    }
    private fun ExpectedPrice(before: Int, discount: Int) {
        output.printOpening(OutputMessages.PROMPT_OPENING_EXPECTED_PRICE)
        output.printPrice(before-discount)
    }

    private fun EventBadge(benefit: Int, planner: EventPlanner) {
        output.printOpening(OutputMessages.PROMPT_OPENING_BADGE)
        output.printOpening(planner.checkBadge(benefit))
    }

    private fun BenefitPrice(price : Int) {
        output.printOpening(OutputMessages.PROMPT_OPENING_TOTAL_BENEFIT)
        output.printPrice(-price)
    }

    private fun BenefitList(planner: EventPlanner) : Pair<Int, Int> {
        var discount = 0
        var benefit = 0
        output.printOpening(OutputMessages.PROMPT_OPENING_BENEFIT_LIST)
        if(planner.isChristmasDDay) {
            benefit += planner.ChristmasDDayBenefit()
            discount += planner.ChristmasDDayBenefit()
            output.printSpecificBenefit("크리스마스 디데이 할인", benefit)
        }
        if(planner.isWeekDay) {
            benefit += planner.WeekDayBenefit()
            discount += planner.WeekDayBenefit()
            output.printSpecificBenefit("평일 할인", planner.WeekDayBenefit())
        }
        if(planner.isWeekEndDay) {
            benefit += planner.WeekEndBenefit()
            discount += planner.WeekEndBenefit()
            output.printSpecificBenefit("주말 할인", planner.WeekEndBenefit())
        }
        if(planner.isStaredDay) {
            benefit += planner.SpecialBenefit()
            discount += planner.SpecialBenefit()
            output.printSpecificBenefit("특별 할인", planner.SpecialBenefit())
        }
        if(planner.isChampange) {
            benefit += planner.GiftBenefit()
            output.printSpecificBenefit("증정 이벤트", planner.GiftBenefit())
        }
        if(benefit == 0) output.printOpening("없음")
        return discount to benefit
    }
    private fun GiftMenu(planner: EventPlanner) {
        output.printOpening(OutputMessages.PROMPT_OPENING_IS_GIFT)
        if(planner.isChampange) output.printOpening("샴페인 1개")
        else output.printOpening("없음")
    }
    private fun MenuList(order: Order) {
        output.printOpening(OutputMessages.PROMPT_OPENING_MENU_LIST)
        order.sheet.forEach { (menu, i) ->
            output.printEachMenu(menu.menuName, i)
        }
    }

    private fun beforeDiscount(order: Order): Int {
        output.printOpening(OutputMessages.PROMPT_OPENING_PRICE_BEFORE_DISCOUNT)
        var total = 0
        order.sheet.forEach { (menu, i) ->
            total += menu.price*i
        }
        output.printPrice(total)
        return total
    }

    private fun inputOrder(): Order {
        output.printOpening(OutputMessages.PROMPT_OPENING_ORDER)
        while(true) {
            try {
                val order = Order(input.readLine())
                return order
            }catch (e : IllegalArgumentException) {
                output.printError(ErrorMessages.INVALID_ORDER)
            }
        }
    }

    private fun inputDate(): Date {
        output.printOpening(OutputMessages.PROMPT_OPENING_DATE)
        while(true) {
            try {
                val date = Date(input.readLine().toIntOrNull() ?: 0)
                return date
            }catch (e : IllegalArgumentException) {
                output.printError(ErrorMessages.INVALID_DATE)
            }
        }
    }
}