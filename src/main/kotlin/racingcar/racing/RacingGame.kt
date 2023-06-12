package racingcar.racing

import racingcar.domain.Cars
import racingcar.domain.RacingGameParam
import racingcar.io.ResultView
import racingcar.util.NumberGenerator
import racingcar.util.RandomNumberGenerator

class RacingGame(
    private val gameParam: RacingGameParam,
    numberGenerator: NumberGenerator = RandomNumberGenerator()
) {
    val cars: Cars = Cars(gameParam.carNameList, numberGenerator)

    fun start() {
        ResultView.printResultMessage()
        repeat(gameParam.rounds) {
            runRound()
        }
    }

    fun runRound() {
        cars.moveAll()
        ResultView.printRound(cars)
    }
}
