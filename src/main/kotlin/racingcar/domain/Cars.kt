package racingcar.domain

import racingcar.util.NumberGenerator

data class Cars(
    private val carNameList: List<String>,
    private val numberGenerator: NumberGenerator
) : Iterable<Car> {
    private val cars = Array(carNameList.size) { index -> Car(carNameList[index], numberGenerator) }

    fun moveAll() {
        cars.forEach { it.move() }
    }

    override fun iterator(): Iterator<Car> =
        cars.iterator()
}
