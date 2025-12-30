package baseball.view

class ConsoleOutputView : OutputView {
    override fun current_line(output: String) {
        print(output)
    }

    override fun next_line(output: String) {
        println(output)
    }
}