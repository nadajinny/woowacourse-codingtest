package christmas.model

import christmas.util.MenuCategory

enum class Menu(
    val menuName: String,
    val category: String,
    val price: Int
) {
    SOUP("양송이수프", MenuCategory.APPETIZER, 6_000),
    TAPAS("타파스", MenuCategory.APPETIZER, 5_500),
    SALAD("시저샐러드", MenuCategory.APPETIZER, 8_000),
    TBON("티본스테이크", MenuCategory.MAIN, 55_000),
    BARBECUE("바비큐립", MenuCategory.MAIN, 54_000),
    SEAFOODPASTA("해산물파스타", MenuCategory.MAIN, 35_000),
    CHRISTMASPASTA("크리스마스파스타", MenuCategory.MAIN, 25_000),
    CAKE("초코케이크", MenuCategory.DESSERT, 15_000),
    ICECREAM("아이스크림", MenuCategory.DESSERT, 5_000),
    ZEROCOLA("제로콜라", MenuCategory.DRINK, 3_000),
    REDWINE("레드와인", MenuCategory.DRINK, 60_000),
    CHAMPAGNE("샴페인", MenuCategory.DRINK, 25_000),
    NONE("없음", MenuCategory.NONE, 0);
    companion object {
        fun from(input: String): Menu = Menu.entries.firstOrNull{ it.menuName == input } ?: Menu.NONE
    }
}
