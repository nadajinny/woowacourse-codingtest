package store.util

import store.model.order.Order
import store.model.product.Products

object Parser {
    fun parseOrder(input: String, products: Products): Order {
        val trimmed = input.replace(" ", "")
        Validator.validateFormat(trimmed)

        val items = trimmed.split(",").map {
            val cleaned = it.removePrefix("[").removeSuffix("]")
            val (name, qty) = cleaned.split("-")
            products.createOrderItem(name, qty.toInt())
        }

        return Order(items)
    }
}
