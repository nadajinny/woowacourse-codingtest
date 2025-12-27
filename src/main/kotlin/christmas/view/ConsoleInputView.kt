package christmas.view

import camp.nextstep.edu.missionutils.Console

class ConsoleInputView : InputView {
    override fun readLine(): String = Console.readLine() ?: ""
}