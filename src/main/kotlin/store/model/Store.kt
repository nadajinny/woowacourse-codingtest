package store.model

class Store (
    val promotions: MutableList<Promotion>,
    val products: MutableList<Product>
){
    var carts = mutableListOf<Cart>()

    fun checkCart() {

    }

}