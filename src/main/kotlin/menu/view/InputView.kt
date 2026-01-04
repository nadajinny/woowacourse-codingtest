package menu.view

import camp.nextstep.edu.missionutils.Console
import menu.model.Coach

class InputView {
    fun readCoachName(): List<Coach> {
        println("코치의 이름을 입력해 주세요. (, 로 구분)")
        val s = Console.readLine()
        val regex = Regex("""^(.+?)(,(.+?))*$""")
        require(regex.matches(s)) { "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요." }
        val names = s.trim().split(",")
        require(names.size in 2..5) { "[ERROR] 코치는 최소 2명 이상 입력해야 합니다."}
        val result = mutableListOf<Coach>()
        names.forEach { text ->
            result.add(Coach(text.trim()))
        }
        println()
        return result
    }

    fun readCoachInfo(coach: Coach) {
        println("${coach.name}(이)가 못 먹는 메뉴를 입력해 주세요.")
        val s = Console.readLine()
        if(s.isNullOrBlank()) return
        require(s.split(",").size in 0..2) {"[ERROR] 코치가 먹지 못하는 음식은 최대 2개까지만 입력해야 합니다."}
        s.split(",").forEach {
            coach.addNope(it.trim())
        }
        println()
    }
}