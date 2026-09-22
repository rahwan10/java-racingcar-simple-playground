package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Car;

class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @Test
    void 쉼표로_구분한_자동차_이름을_자동차_목록으로_변환한다() {
        List<Car> cars = inputValidator.createCars("pobi, crong");

        assertThat(cars).extracting(Car::getName)
                .containsExactly("pobi", "crong");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,", ",", "pobi,,crong"})
    void 비어_있는_자동차_이름은_예외를_발생시킨다(String inputNames) {
        assertThatThrownBy(() -> inputValidator.createCars(inputNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1자 이상 5자 이하여야 합니다.");
    }

    @Test
    void 시도_횟수_문자열을_정수로_변환한다() {
        int tryCount = inputValidator.parseTryCount("3");

        assertThat(tryCount).isEqualTo(3);
    }

    @Test
    void 숫자가_아닌_시도_횟수는_예외를_발생시킨다() {
        assertThatThrownBy(() -> inputValidator.parseTryCount("three"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void 시도_횟수는_1_이상이어야_한다(String inputTryCount) {
        assertThatThrownBy(() -> inputValidator.parseTryCount(inputTryCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
