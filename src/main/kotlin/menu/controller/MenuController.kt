package menu.controller

import menu.model.Menu
import menu.view.InputView
import menu.view.OutputView

class MenuController {
    private val input = InputView()
    private val output = OutputView()


    fun run() {
        val menu = Menu()
        input.readCoachName()
        menu.runWeeklyMenu()

    }


}