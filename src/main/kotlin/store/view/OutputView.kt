package store.view

import store.model.Convenience

interface OutputView {
    fun opening() {}
    fun opening_show_inventory() {}
    fun show_inventory(name: String, price: Int, quantity: Int, promotion: String) {}
    fun purchase_guide() {}
    fun next_line() {}
    fun Error(e : String) {}
}