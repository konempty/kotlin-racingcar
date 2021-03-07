package racingcar.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PositiveCountTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 369])
    fun `자연수의 경우 객체가 생성된다`(value: Int) {
        val result = PositiveCount(value)
        assertThat(result).isNotNull
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "10", "369"])
    fun `자연수인 문자열의 경우 객체 생성이 가능하다`(value: String) {
        val result = PositiveCount(value)
        assertThat(result).isNotNull
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "a", "="])
    fun `자연수가 아닌 문자열의 경우 예외가 발생한다`(value: String) {
        val expectedMessage = "자연수이어야 합니다"
        val result = assertThrows<IllegalArgumentException> { PositiveCount(value) }
        assertThat(result.message).isEqualTo(expectedMessage)
    }
}