package store.model

class Convenience(products: MutableList<String>, promotions: MutableList<String>) {
    val inventory = mutableListOf<Product>()
    val promotion = mutableListOf<Promotion>()
    init {
        var i = true
        for (data in products) {
            if(i) {
                i = false
                continue
            }
            inventory.add(Product(data))
        }
        i = true
        for (data in promotions) {
            if(i) {
                i = false
                continue
            }
            promotion.add(Promotion(data))
        }
    }
}