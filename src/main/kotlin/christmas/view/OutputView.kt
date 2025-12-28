package christmas.view

interface OutputView {
    fun printOpening(i: String) {}
    fun nextLine() {}
    fun printError(e: String) {}
    fun printOpeningEvent(i: Int) {}
    fun printEachMenu(menu: String, count: Int) {}
    fun printPrice(price: Int) {}

}
