package store.model

import java.util.Date


class Promotion(
    val name: String,
    val buy: Int,
    val get: Int,
    val startDate: Date,
    val endDate: Date
) {
}