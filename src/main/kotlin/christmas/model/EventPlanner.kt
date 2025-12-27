package christmas.model

class EventPlanner(
    public val date : Date,
    public val order : Order
) {
    fun checkGift () : Boolean {
        if(order.getTotalPrice() >= 120_000) return true
        return false
    }

}