package christmas.model

import christmas.util.ErrorMessages


private const val APPETIZER = "에피타이저"
private const val MAIN = "메인"
private const val DESSERT = "디저트"
private const val DRINK = "음료"
enum class Menu(
    val menuName: String,
    val category: String,
    val price: Int
) {
    SOUP("양송이수프", APPETIZER,6_000),
    TABAS("타파스", APPETIZER,5_500),
    SALAD("시저샐러드", APPETIZER, 8_000),
    TBON("티본스테이크", MAIN, 55_000),
    BARBECUELIP("바비큐립", MAIN ,54_000),
    SEAFOODPASTA("해산물파스타", MAIN ,35_000),
    CHRISTMASPASTA("크리스마스파스타", MAIN ,25_000),
    CHOCOLATECAKE("초코케이크", DESSERT, 15_000),
    ICECREAM("아이스크림", DESSERT, 5_000),
    ZEROCOLA("제로콜라", DRINK, 3_000),
    REDWINE("레드와인", DRINK, 60_000),
    CHAMPAGNE("샴페인", DRINK, 25_000);
    companion object {
        fun from(menuName: String) : Menu =
            values().firstOrNull { it.menuName == menuName } ?: throw IllegalArgumentException(ErrorMessages.MENU_NOT_IN_TEMPLATE)
    }

}