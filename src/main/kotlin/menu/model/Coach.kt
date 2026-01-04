package menu.model

class Coach(
    val name: String
) {
   private val menu = mutableListOf<String>()
   private val nope = mutableListOf<String>()

    fun alreadyAte(food: String): Boolean { //이미 배정이 된 상태
        if(menu.contains(food)) return true
        return false
    }

    fun cannotEat(food: String): Boolean { //못 먹는 음식
        if(nope.contains(food)) return true
        return false
    }

    fun addMenu(food: String) { //정해지고 난 다음에 배정된 음식으로 추가
        menu.add(food)
    }

    fun addNope(food: String) { //못먹는 음식을 초반에 입력받을 때
        nope.add(food)
    }

}