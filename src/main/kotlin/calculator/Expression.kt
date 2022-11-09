package calculator

data class Expression(
    private val input: String?
) {
    private val expression: List<String>

    init {
        require(!input.isNullOrBlank()) { "잘못된 입력입니다. 계산식을 입력해주세요" }
        require(Regex(OPERATION_REGULAR_EXPRESSION).containsMatchIn(input)) { "잘못된 입력입니다. 숫자, 사칙연산 만 입력해주세요" }
        require(!input.startsWith(DELIMITER_WHITE_SPACE)) { "잘못된 입력입니다. 연산식은 공백으로 시작할 수 없습니다." }
        expression = input.split(DELIMITER_WHITE_SPACE)
        checkIfExpressionIsValid(expression)
    }

    private fun checkIfExpressionIsValid(expression: List<String>) =
        expression.forEachIndexed { index, exp ->
            if ((index.isNumberIndex() && exp.contains(OPERATOR_REGULAR_EXPRESSION.toRegex())) ||
                (index.isOperatorIndex() && exp.contains(NUMBER_REGULAR_EXPRESSION.toRegex()))
            ) {
                throw IllegalArgumentException("잘못된 입력입니다. 숫자, 연산자 입력 순서 확인해주세요")
            }
        }

    private fun Int.isNumberIndex() =
        this % NUMBER_PATTERN_INDEX == 0

    private fun Int.isOperatorIndex() =
        !this.isNumberIndex()

    fun partitionExpression(): Pair<List<Double>, List<String>> =
        expression.withIndex()
            .partition { it.index % NUMBER_PATTERN_INDEX == 0 }
            .let { (numbers, operators) ->
                numbers.map { num -> num.value.toDouble() } to operators.map { it.value }
            }

    companion object {
        private const val DELIMITER_WHITE_SPACE = " "
        private const val OPERATION_REGULAR_EXPRESSION = "^[+\\-*/\\d\\s+]*$"
        private const val NUMBER_REGULAR_EXPRESSION = "^[0-9]*\$"
        private const val OPERATOR_REGULAR_EXPRESSION = "\"[+\\-*/]\""
        private const val NUMBER_PATTERN_INDEX = 2
    }
}