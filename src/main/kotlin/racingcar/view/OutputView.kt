package racingcar.view

interface OutputView {
    fun printCarName() {}
    fun printCount() {}
    fun printResult() {}
    fun printRaceRecord(name: String, record: Int) {}
    fun printWinner(winner: List<String>) {}
    fun printNextLine() {}
}