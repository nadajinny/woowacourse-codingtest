package christmas.view

interface OutputView {
    fun printOpening() {}
    fun nextLine() {}
    fun printOpeningDate() {}
    fun printError(e: String) {}
    fun printOpeningOrder() {}
}