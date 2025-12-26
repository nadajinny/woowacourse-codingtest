package calculator.controller

import calculator.module.Calculator
import camp.nextstep.edu.missionutils.Console

class CalculatorController {
    fun run() {
        println("덧셈할 문자열을 입력해 주세요. ")
        val input = Console.readLine()
        val calculator = Calculator(input)
        val output = calculator.run()
        println("결과 : $output")
    }
}