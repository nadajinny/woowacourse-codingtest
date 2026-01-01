package store.util

import store.model.cart.Cart
import store.model.cart.Item

class Parser {
    fun parseCart(input: String): Cart {
        val trimmed = input.replace(" ", "")
        Validator().validateFormat(trimmed)

        val items = trimmed.split(",").map {
            val cleaned = it.removePrefix("[").removeSuffix("]").split("-")
            val (name, count) = cleaned
            Item(name, count.toInt())
        }
        return Cart(items)
    }
}