package store.model.discount

class MembershipDiscount {
    fun calculate(amount: Int): Int {
        return minOf(amount * 3 / 10, 8_000)
    }
}
