package store.view

import camp.nextstep.edu.missionutils.Console
import java.util.IllformedLocaleException

class InputView {
    fun readPromotion() {

    }

    fun readContinue(): Boolean {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
        return Console.readLine() == "Y"
    }
}