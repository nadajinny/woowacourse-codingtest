package christmas.model

import christmas.util.ErrorMessages

class Date(
    private val date: Int
) {
    init {
        require(date in 1..31) { ErrorMessages.INVALID_DATE }
    }
}