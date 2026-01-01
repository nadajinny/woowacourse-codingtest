package store.model.promotion

import java.io.File
import java.time.LocalDate

class PromotionRepository(
    private val promotions: Map<String, Promotion>
) {

    companion object {
        fun fromFile(): PromotionRepository {
            val lines = File("src/main/resources/promotions.md")
                .readLines()
                .drop(1) // header 제거

            val map = lines.associate {
                val (name, buy, get, start, end) = it.split(",")
                name to Promotion(
                    name,
                    buy.toInt(),
                    get.toInt(),
                    LocalDate.parse(start),
                    LocalDate.parse(end)
                )
            }
            return PromotionRepository(map)
        }
    }

    fun find(name: String?): Promotion? {
        if (name == null || name == "null") return null
        return promotions[name]
    }
}