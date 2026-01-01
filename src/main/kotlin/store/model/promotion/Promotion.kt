package store.model.promotion

import java.io.File
import java.time.LocalDate

class Promotion(
    val promotionTypes: Map<String, PromotionType>
) {
    companion object {
        fun fromFile(): Promotion {
            val lines = File("src/main/resources/promotions.md")
                .readLines()
                .drop(1)

            val map = lines.associate {
                val (name, buy, get, start, end) = it.split(",")
                name to PromotionType(
                    name,
                    buy.toInt(),
                    get.toInt(),
                    LocalDate.parse(start),
                    LocalDate.parse(end)
                )
            }
            return Promotion(map)
        }
    }
    fun find(type: String): PromotionType? {
        if(type == "null") return null
        return promotionTypes.get(type)
    }
}