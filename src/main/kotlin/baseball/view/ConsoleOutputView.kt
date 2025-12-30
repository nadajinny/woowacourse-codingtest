package baseball.view

class ConsoleOutputView : OutputView {
    override fun print(output: String) {
        print(output)
    }

    override fun println(output: String) {
        println(output)
    }
}