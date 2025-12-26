package racingcar.model

import camp.nextstep.edu.missionutils.Randoms
import racingcar.view.InputView
import racingcar.view.OutputView

class CarRace(
    private val input: InputView,
    private val output: OutputView
) {
    var cars = mutableListOf<Car>()

    fun splitCarName(carNames: String) {
        if(carNames.isBlank()) throw IllegalArgumentException()
        val speratedCarNames = carNames.split(",").map { it.trim() }
        speratedCarNames.forEach { name ->
            cars.add(Car(name))
        }
    }//차 이름 분리 메서드

    fun raceCount(cnt: String) {
        if(cnt.isBlank()) throw IllegalArgumentException()
        val count = cnt.trim().toInt()
        if(count <= 0) throw IllegalArgumentException()
        output.printResult()
        (1 .. count).forEach { _ ->
            startRace()
            output.printNextLine()
        }
    }//입력된 실행횟수 검사 & 횟수 별로 시행

    fun startRace() {
        cars.forEach { car ->
            val move = Randoms.pickNumberInRange(0, 9)
            checkToMove(move, car)
            output.printRaceRecord(car.getName(), car.getDistance())
        }
    }//실행횟수마다 -> carList마다 random으로 돌리고 checktomove로 전달

    fun checkToMove(move: Int, car: Car) {
        if(move >=4) {
            car.add()
        }//전달받은 car의 distance 증가시키는 메서드 추가
    }


    fun checkWinner() {
        val distances = mutableListOf<Int>()
        cars.forEach { car ->
            distances.add(car.getDistance())
        }
        val maxDistances = distances.max()
        val winner = mutableListOf<String>()
        cars.forEach { car ->
            if(car.getDistance() == maxDistances) winner.add(car.getName())
        }
        output.printWinner(winner)
    }



}