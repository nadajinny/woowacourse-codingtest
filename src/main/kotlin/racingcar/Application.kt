package racingcar

import racingcar.controller.RacingCarController
import racingcar.view.ConsoleInputView
import racingcar.view.ConsoleOutputView

fun main() {
    // TODO: 프로그램 구현
    val inputView = ConsoleInputView()
    val outputView = ConsoleOutputView()

    val racingCar = RacingCarController(inputView, outputView)
    racingCar.run()
}
