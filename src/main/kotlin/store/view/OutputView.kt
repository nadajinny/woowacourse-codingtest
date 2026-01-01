package store.view

import store.model.order.Receipt
import store.model.product.Products

class OutputView {
    fun printWelcome(products: Products) {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.\n")
        products.print()
    }

    fun printReceipt(receipt: Receipt) {
        println("\n=============W 편의점==============")
        println("상품명\t\t수량\t금액")
        receipt.purchases().forEach {
            println("${it.name}\t\t${it.quantity}\t${formatMoney(it.price)}")
        }

        println("=============증\t정==============")
        receipt.gifts().forEach {
            println("${it.name}\t\t${it.quantity}")
        }

        println("================================")
        println("총구매액\t${receipt.totalQuantity()}\t${formatMoney(receipt.totalPrice())}")
        println("행사할인\t\t-${formatMoney(receipt.promotionDiscount())}")
        println("멤버십할인\t\t-${formatMoney(receipt.membershipDiscount())}")
        println("내실돈\t\t${formatMoney(receipt.finalPrice())}")
    }

    private fun formatMoney(amount: Int): String {
        return String.format("%,d", amount)
    }

}
