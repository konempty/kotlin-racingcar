package racingcar.racing

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import racingcar.domain.Car
import racingcar.domain.RacingGameParam
import racingcar.util.Constant
import racingcar.util.OrderNumberGenerator

class RacingTest : BehaviorSpec({
    val carCount = 10
    val rounds = 10
    lateinit var target: RacingGame

    beforeEach {
        target = RacingGame(
            RacingGameParam((0 until carCount).map { it.toString() }, rounds),
            OrderNumberGenerator(*(Constant.MIN_GENERATED_NUMBER..Constant.MAX_GENERATED_NUMBER).toList().toIntArray())
        )
    }

    given("runRound 메소드") {
        `when`("생성되는 숫자가 다르면") {
            then("전진여부가 달라진다") {
                target.runRound()
                target.cars.forEachIndexed { index, car ->
                    car.getPosition() shouldBe if (index < Car.MOVE_THRESHOLD) Car.START_POSITION else Car.START_POSITION + Car.STEP
                }
            }
        }
    }

    given("start 메소드") {
        `when`("생성되는 숫자가 다르면") {
            then("전진여부가 달라진다") {
                target.start()
                target.cars.forEachIndexed { index, car ->
                    car.getPosition() shouldBe if (index < Car.MOVE_THRESHOLD) Car.START_POSITION else Car.START_POSITION + Car.STEP * rounds
                }
            }
        }
    }

    given("getWinner 메소드") {
        `when`("움직인 여부가 다르면") {
            then("승패가 정해진다") {
                target.start()
                target.getWinners().size shouldBe 6
            }
        }
    }
})
