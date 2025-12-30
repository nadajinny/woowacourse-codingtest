package baseball.controller

import baseball.model.Baseball
import baseball.view.InputView
import baseball.view.OutputView
import baseball.util.OutputMessages as m

class BaseballController(
    private val input: InputView,
    private val output: OutputView
) {
    fun run() {
        output.next_line(m.START_BASEBALL)
        while(true) {
            val game = Baseball()
            createRandomNumber(game)
            gameStart(game)
            if(restart()=="2") break
        }
    }

    fun gameStart(game: Baseball) {
        while(true){
            output.current_line(m.REQUIRE_NUMBER)
            val number = input.readLine()
            val key = changeStringToNumber(number)
            val (strike, ball) = game.check(key)
            if(evaluate_result(strike, ball)) break
        }
    }

    fun evaluate_result(strike: Int, ball: Int): Boolean {
        if(strike == 3) {
            output.next_line("${strike}" + m.STRIKE)
            output.next_line(m.RESTART)
            return true
        }
        if(ball != 0&&strike == 0) {
            output.next_line("${ball}" + m.BALL)

        }
        if(ball == 0 && strike != 0 ){
            output.next_line("${strike}"+m.STRIKE)
        }
        if(ball != 0 && strike != 0) {
            output.next_line("${ball}"+m.BALL+" "+"${strike}"+m.STRIKE)
        }

        if(ball == 0 && strike == 0) {
            output.next_line(m.NOTHING)
        }
        return false
    }

    fun changeStringToNumber(input: String): MutableList<Int> {
        val result = mutableListOf<Int>()
        input.forEach { i ->
            result.add(i.digitToIntOrNull() ?: throw IllegalArgumentException("잘못된 값 입력"))
        }
        if(result.size != 3) throw IllegalArgumentException("세자리 수 이하입니다.")
        return result
    }

    fun createRandomNumber(game: Baseball) {
        game.create()
    }

    fun restart(): String {
        return input.readLine()
    }
}