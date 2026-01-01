package store

import store.contoller.ConvenienceController
import store.view.ConsoleInputView
import store.view.ConsoleOutputView
import java.io.File
import java.io.FileReader


fun main() {
    // TODO: 프로그램 구현
    val input = ConsoleInputView()
    val output = ConsoleOutputView()

    val controller = ConvenienceController(input, output)
    controller.run()
}
