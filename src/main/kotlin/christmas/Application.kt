package christmas

import christmas.controller.EventPlannerContoller
import christmas.model.EventPlanner
import christmas.view.ConsoleInputView
import christmas.view.ConsoleOutputView

fun main() {
    val inputview = ConsoleInputView()
    val outputview = ConsoleOutputView()
    val controller = EventPlannerContoller(inputview, outputview)
    controller.run()
}
