package store.view

import camp.nextstep.edu.missionutils.Console
import java.io.File
import java.io.FileReader

class ConsoleInputView: InputView {
    override fun readLine(): String = Console.readLine() ?: ""
    override fun readProduct(): MutableList<String> {
        val path = System.getProperty("user.dir") + "/src/main/resources"
        val fileName = "products.md"
        val file = File(path, fileName)
        val fileReader = FileReader(file)
        val product = mutableListOf<String>()
        fileReader.readLines().forEach {
            product.add(it)
        }
        return product
    }

    override fun readPromotion(): MutableList<String> {
        val path = System.getProperty("user.dir") + "/src/main/resources"
        val fileName = "promotions.md"
        val file = File(path, fileName)
        val fileReader = FileReader(file)
        val promotion = mutableListOf<String>()
        fileReader.readLines().forEach {
            promotion.add(it)
        }
        return promotion
    }
}