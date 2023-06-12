package racingcar.io

import racingcar.domain.RacingGameParam

object InputView {
    private const val CAR_NAME_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."
    private const val ROUND_COUNT_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?"
    private const val SEPARATOR = ","

    fun getGameParam(): RacingGameParam {
        val carNameList = getCarNames()
        val rounds = getRoundCount()

        return RacingGameParam(carNameList, rounds)
    }

    private fun getCarNames(): List<String> {
        println(CAR_NAME_INPUT_MESSAGE)
        return readlnOrNull()!!.split(SEPARATOR)
    }

    private fun getRoundCount(): Int {
        println(ROUND_COUNT_INPUT_MESSAGE)
        return readlnOrNull()!!.toInt()
    }
}
