package menu.view

import menu.model.Coach

class OutputView {
    fun printWeeklyMenu(categories: List<String>) {
        println("메뉴 추천 결과입니다.\n" +
                "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]")
        print("[ 카테고리 ")
        categories.forEach { category->
            print("|")
            print(" $category ")
        }
        println("]")
    }

    fun printMenuSet(coach: Coach) {
        print("[ ${coach.name} ")
        coach.menu.forEach {
            print("|")
            print(" $it ")
        }
        println("]")
    }

    fun printEnding() {
        println("\n" +
                "추천을 완료했습니다.")
    }

    fun printOpening() {
        println("점심 메뉴 추천을 시작합니다.\n")
    }
}