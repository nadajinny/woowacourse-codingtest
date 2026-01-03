package store.model

class Store (
    val promotions: MutableList<Promotion>,
    val products: MutableList<Product>
){
    var carts = mutableListOf<Cart>()

    fun checkCart() {
        carts.forEach { cart ->
            require(products.any({ it.name == cart.name })) { "존재하지 않는 상품입니다. 다시 입력해 주세요." }
            var item = 0
            products.forEach { product ->
                if(product.name == cart.name) item += product.quantity
            }
            require(item >= cart.quantity) { "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요." }
        }
    }

}