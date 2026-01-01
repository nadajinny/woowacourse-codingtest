package store.model.promotion

enum class PromotionType(private val base: Int) {
    BUY_ONE_GET_ONE(1),
    BUY_TWO_GET_ONE(2),
    BUY_THREE_GET_ONE(3);

    fun freeCount(buy: Int): Int {
        return buy/base
    }
}