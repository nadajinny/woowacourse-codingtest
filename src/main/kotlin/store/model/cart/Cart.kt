package store.model.cart

import store.model.stock.InStock


class Cart(
    var items: List<Item>
) {
    fun checkQuantity(inStock: InStock) {
        items.forEach {
            val product = inStock.checkProduct(it.name)
            if(it.count > product.totalCount) throw IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.")
        }
    }
}