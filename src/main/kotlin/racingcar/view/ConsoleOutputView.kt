package racingcar.view

import racingcar.util.OutputMessages

class ConsoleOutputView: OutputView {
    override fun printCarName() {
        println(OutputMessages.PROMPT_CAR_NAME)
    }

    override fun printCount() {
        println(OutputMessages.PROMPT_COUNT)
    }

    override fun printResult() {
        println(OutputMessages.PROMPT_RESULT)
    }

    override fun printRaceRecord(name : String, record: Int) {
        println("$name : " + "-".repeat(record))
    }

    override fun printWinner(winner: List<String>) {
        println(OutputMessages.PROMPT_WINNER+"${winner.joinToString(", ")}")
    }

    override fun printNextLine() {
        println()
    }
}