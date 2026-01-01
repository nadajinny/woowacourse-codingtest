package store.view

import store.model.Convenience

interface OutputView {
    fun opening() {}
    fun show_inventory(convenience: Convenience) {}
    fun purchase_guide() {}
    fun next_line() {}
}