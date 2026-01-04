package menu.model

import camp.nextstep.edu.missionutils.Randoms

class Menu {
    private val japaneseFood = listOf<String>("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼")
    private val koreanFood = listOf<String>("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음")
    private val chineseFood = listOf<String>("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채")
    private val asianFood = listOf<String>("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜")
    private val westernFood = listOf<String>("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니")

    val categories = listOf<String>("일식", "한식", "중식", "아시안", "양식")
    val dayCategories = mutableListOf<String>()
    var coaches = listOf<Coach>()

    fun restoreCoachInfo(info : List<Coach>) {
        coaches = info
    }

    fun runWeeklyMenu()  {
        for (i in 1..5) {
            categoryChoice()
        }
    }

    fun categoryChoice() {
        var category: String
        do {
            category = categories[Randoms.pickNumberInRange(1, 5)]
        } while(dayCategories.filter{it == category}.size < 2)
        dayCategories.add(category)
        menuChoice(category)
    }
    fun menuChoice(category: String) { //각각의 코치들이 메뉴를 선택하는 로직으로 전달되는 함수
        coaches.forEach { coach ->
            menuChoiceByCoach(checkCategory(category), coach)
        }
    }

    fun checkCategory(category: String): List<String> {
        return when(category) {
            "일식" -> japaneseFood
            "한식" -> koreanFood
            "중식" -> chineseFood
            "아시안" -> asianFood
            else -> westernFood
        }
    }
    fun menuChoiceByCoach(menus: List<String>, coach: Coach) { //메뉴 선택 로직
        var menu: String
        do {
            menu = Randoms.shuffle(menus)[0]
        } while(!coach.alreadyAte(menu)&&!coach.cannotEat(menu))
        coach.addMenu(menu)
    }


}