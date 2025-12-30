package baseball.model

import camp.nextstep.edu.missionutils.Randoms

class Baseball {
    val answer = mutableListOf<Int>()

    fun create() {
        while(answer.size < 3) {
            val randomNumber = Randoms.pickNumberInRange(1, 9)
            if(!answer.contains(randomNumber)) answer.add(randomNumber)
        }
    }
    fun check(key: List<Int>): Pair<Int, Int> {
        var strike = 0
        var ball = 0
        if(answer.contains(key[0])) {
            if(answer[0] == key[0]) strike ++
            else ball ++
        }
        if(answer.contains(key[1])){
            if(answer[1]==key[1]) strike++
            else ball++
        }
        if(answer.contains(key[2])) {
            if(answer[2]==key[2]) strike++
            else ball++
        }
        return strike to ball
    }
}