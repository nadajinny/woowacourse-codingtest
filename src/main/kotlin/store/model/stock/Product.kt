package store.model.stock

import store.model.promotion.PromotionType

class Product(
    val name: String,
    val price: Int,
    var totalCount: Int,
    var promotionCount: Int,
    var promotionType: PromotionType?
) {

}