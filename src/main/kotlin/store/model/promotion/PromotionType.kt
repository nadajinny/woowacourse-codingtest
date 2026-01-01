package store.model.promotion

import java.time.LocalDate

class PromotionType(
    val name: String,
    val buy: Int,
    val get: Int,
    val start: LocalDate,
    val end: LocalDate
) {

}