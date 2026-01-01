package store.model

import store.view.OutputView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Convenience(products: MutableList<String>, promotions: MutableList<String>, output: OutputView) {
    val is_promotion_inventory = mutableListOf<Inventory>()
    val none_promotion_inventory = mutableListOf<Inventory>()
    val promotion_Info = mutableListOf<PromotionInfo>()

    val purchaseBox = mutableListOf<Purchase>()

    init {
        output.opening_show_inventory()
        var i = true
        for (data in products) {
            if(i) {
                i = false
                continue
            }
            val splitedInfo = data.split(",")
            val name = splitedInfo[0]
            require(!name.trim().isEmpty()) {"문자열이 비어있음"}
            val price = splitedInfo[1].toIntOrNull() ?: 0
            val quantity = splitedInfo[2].toIntOrNull() ?: 0
            val promotion = splitedInfo[3]
            require(!name.trim().isEmpty()) {"문자열이 비어있음"}
            if(promotion == "null") none_promotion_inventory.add(Inventory(name, price, quantity, promotion))
            else is_promotion_inventory.add(Inventory(name, price, quantity, promotion))
            output.show_inventory(name, price, quantity, promotion)
        }
        output.next_line()
        i = true
        for (data in promotions) {
            if(i) {
                i = false
                continue
            }

            val splitedInfo = data.split(",")
            val name = splitedInfo[0].trim()
            val buy = splitedInfo[1].trim().toIntOrNull() ?: 0
            val get = splitedInfo[2].trim().toIntOrNull() ?: 0
            var date = splitedInfo[3].trim()
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            val start_date = format.parse(date)
            date = splitedInfo[4].trim()
            val cal = Calendar.getInstance()
            cal.time = format.parse(date)
            cal.add(Calendar.DAY_OF_MONTH,1)
            cal.add(Calendar.MILLISECOND, -1)
            val end_date = cal.time
            promotion_Info.add(PromotionInfo(name, buy, get, start_date, end_date))
        }

    }

    fun checkPurchaseList(info : String) {
        val box = info.split(",")
        box.forEach { s ->
            val cleaned = s.replace("[", "").replace("]","").trim()
            val splitItem = cleaned.split("-")
            require(splitItem.size == 2) { "[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요." }
            purchaseBox.add(Purchase(splitItem[0], splitItem[1].toInt()))
        }
    }

    fun validate_purchase() {
        purchaseBox.forEach { purchase ->
            val promo = is_promotion_inventory.find { it.name == purchase.name }
            val non_promo = none_promotion_inventory.find {it.name == purchase.name}
            val inventory = promo ?: non_promo ?: throw IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.")
            val promo_count = promo?.quantity ?: 0
            val non_promo_count = non_promo?.quantity ?: 0
            require(purchase.count < promo_count + non_promo_count) { "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요." }
        }
    }

}