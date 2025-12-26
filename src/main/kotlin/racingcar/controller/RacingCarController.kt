package racingcar.controller

import racingcar.model.CarRace
import racingcar.view.InputView
import racingcar.view.OutputView

class RacingCarController(
    private val input: InputView,
    private val output: OutputView
) {
    fun run() {
        output.printCarName()
        val racing = CarRace(input, output)
        racing.splitCarName(input.readLine())
        output.printNextLine()
        output.printCount()
        racing.raceCount(input.readLine())
        racing.checkWinner()
    }
}