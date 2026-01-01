package store.util

object Validator {
    fun validateFormat(input: String) {
        val pattern = Regex("""^\[[^\[\],-]+-\d+](,\[[^\[\],-]+-\d+])*$""")
        if (!input.matches(pattern)) {
            throw IllegalArgumentException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.")
        }
    }
}
