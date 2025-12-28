package christmas.view

import camp.nextstep.edu.missionutils.Console

class InputViewConsole: InputView {
    override fun readLine(): String = Console.readLine() ?: ""
}