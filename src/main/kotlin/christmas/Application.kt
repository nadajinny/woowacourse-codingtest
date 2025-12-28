package christmas

import christmas.controller.EventPlannerController
import christmas.view.ConsoleInputView
import christmas.view.ConsoleOutputView

fun main() {
    val input = ConsoleInputView()
    val output = ConsoleOutputView()

    val controller = EventPlannerController(input, output)
    controller.run()
}
