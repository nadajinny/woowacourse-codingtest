package oncall.view

import camp.nextstep.edu.missionutils.Console

class InputView() {
    fun readMonth() : Pair<Int, String> {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val s = Console.readLine()
        println()
        val trimmedS = s.trim().split(",")
        require(trimmedS.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val month = trimmedS[0].toIntOrNull() ?: 0
        require(month in 1..12) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val dayOfWeek = trimmedS[1].trim()
        val dayOfWeekList = listOf<String>("일", "월", "화","수", "목", "금","토")
        require(dayOfWeekList.contains(dayOfWeek)) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return month to dayOfWeek
    }

    fun readWeekDayWorker(): MutableList<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val s = Console.readLine()
        println()
        val trimmedS = s.trim().split(",")
        require(trimmedS.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val name = mutableListOf<String>()
        trimmedS.forEach { s ->
            require(s.length <= 5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            require(!name.contains(s)) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            name.add(s)
        }
        return name
    }

    fun readWeekEndWorker(dayWorker: MutableList<String>): MutableList<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val s = Console.readLine()
        println()
        val trimmedS = s.trim().split(",")
        val name = mutableListOf<String>()
        trimmedS.forEach { s ->
            require(!name.contains(s)) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            require(dayWorker.contains(s)) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            name.add(s)
        }
        return name
    }
}