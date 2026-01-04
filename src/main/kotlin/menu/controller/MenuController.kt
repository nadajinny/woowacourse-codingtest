package menu.controller

import menu.model.Coach
import menu.model.Menu
import menu.view.InputView
import menu.view.OutputView

class MenuController {
    private val input = InputView()
    private val output = OutputView()


    fun run() {
        val menu = Menu()
        output.printOpening()
        menu.coaches = input.readCoachName()
        readFoodInfo(menu.coaches)
        menu.runWeeklyMenu()
        output.printWeeklyMenu(menu.dayCategories)
        menu.coaches.forEach { coach ->
            output.printMenuSet(coach)
        }
        output.printEnding()
    }

    fun readFoodInfo(coaches: List<Coach>) {
        coaches.forEach { coach ->
            input.readCoachInfo(coach)
        }
    }


}