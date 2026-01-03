package oncall.controller

import oncall.model.Oncall
import oncall.view.InputView
import oncall.view.OutputView

class OncallController  {
    private val input = InputView()
    private val output = OutputView()

    fun run() {
        val oncall = Oncall(input, output)
        oncall.readMonth()
        oncall.readWorker()
        oncall.replaceMonth()
        oncall.checkWorker()
        oncall.printResult()
    }
}