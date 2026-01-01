package store.model.promotion

import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate

class Promotion(
    val name: String,
    val buy: Int,
    val get: Int,
    private val start: LocalDate,
    private val end: LocalDate
) {

    fun isActive(): Boolean {
        val now = DateTimes.now().toLocalDate()
        return !now.isBefore(start) && !now.isAfter(end)
    }

    fun freeQuantity(quantity: Int): Int {
        if (quantity < buy) return 0
        return (quantity / buy) * get
    }

    fun additionalNeeded(quantity: Int): Int {
        val remainder = quantity % buy
        if (remainder == 0) return 0
        return buy - remainder
    }
}
