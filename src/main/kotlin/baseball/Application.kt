package baseball

import baseball.controller.BaseballController
import baseball.view.ConsoleInputView
import baseball.view.ConsoleOutputView

fun main() {
    val input = ConsoleInputView()
    val output = ConsoleOutputView()

    val controller = BaseballController(input, output)
    controller.run()
}
