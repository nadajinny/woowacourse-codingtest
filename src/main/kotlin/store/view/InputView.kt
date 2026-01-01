package store.view

interface InputView {
    fun readLine() : String
    fun readProduct() : MutableList<String>
    fun readPromotion() : MutableList<String>
}
