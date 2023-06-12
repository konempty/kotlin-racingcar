package racingcar.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import racingcar.util.Constant
import racingcar.util.OrderNumberGenerator

class CarTest : BehaviorSpec({
    given("movable 메소드") {
        forAll(
            table(
                headers("number", "expected"),
                *(Constant.MIN_GENERATED_NUMBER until Car.MOVE_THRESHOLD).map { row(it, false) }.toTypedArray(),
                *(Car.MOVE_THRESHOLD..Constant.MAX_GENERATED_NUMBER).map { row(it, true) }.toTypedArray()
            )
        ) { number, expected ->
            `when`("${number}가 나오면") {
                then("${expected}를 반환한다") {
                    val car = Car(OrderNumberGenerator(number))
                    car.movable() shouldBe expected
                }
            }
        }
    }

    given("move 메소드") {
        `when`("movable이 true를 반환하면") {
            then("position을 ${Car.STEP}만큼 증가시킨다") {
                val car = Car(OrderNumberGenerator(Car.MOVE_THRESHOLD))
                car.move()
                car.getPosition() shouldBe Car.START_POSITION + Car.STEP
            }
        }

        `when`("movable이 false를 반환하면") {
            then("position을 증가시키지 않는다") {
                val car = Car(OrderNumberGenerator(Car.MOVE_THRESHOLD - 1))
                car.move()
                car.getPosition() shouldBe Car.START_POSITION
            }
        }
    }
})
