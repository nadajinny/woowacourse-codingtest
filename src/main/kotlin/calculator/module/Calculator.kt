package calculator.module

class Calculator(var input: String) {

    private val spliter = mutableListOf<String>(",", ":")
    fun inputNullOrBlank() {
        if(input.isNullOrBlank()) throw IllegalArgumentException("빈 문자열 혹은 Null값이 입력되었습니다.")
    }

    fun checkCustomSpliter() {
        if(input.startsWith("//")) {
            val newLineIndex = input.indexOf("\n")

            if(newLineIndex == -1) {
                throw IllegalArgumentException("커스텀 구분자 입력 문구가 들어오고 닫히지 않았습니다.")
            }

            val customSpliter = input.substring(2, newLineIndex)

            if(customSpliter.isBlank()) {
                throw IllegalArgumentException("커스텀 구분자 입력에 아무것도 입력되지 않았습니다.")
            }

            spliter.add(customSpliter)

            input = input.substring(newLineIndex+1)
        }


    }

    fun checkBlank(line: String, which: String) {
        if(line.isBlank()) throw IllegalArgumentException("$which 문자열이 비어있습니다.")
    }

    fun splitInput(): List<String> {
        input = input.trim()
        val splitRegex = spliter.joinToString("|") { Regex.escape(it) }.toRegex()

        val tokens = input.split(splitRegex)
        return tokens
    }

    fun tokensStringToInt(tokens: List<String>): List<Int> {
        val intTokens = tokens.map { token ->
            if(token.isBlank()) throw IllegalArgumentException("구분자 사이가 비어있습니다.")
            val number = token.toIntOrNull() ?: throw IllegalArgumentException("잘못된 숫자가 입력되어있습니다.")
            if(number <= 0) throw IllegalArgumentException("입력된 숫자가 양수가 아닙니다.")
            number
        }
        return intTokens
    }

    fun add(token: List<Int>): Int {
        return token.sum()
    }

    fun run(): Int {
        input = input.replace("\\n", "\n")
        input.trim()
        inputNullOrBlank()
        checkCustomSpliter()
        checkBlank(input, "입력값")
        val tokens = splitInput()
        val intTokens = tokensStringToInt(tokens)
        return add(intTokens)
    }
}