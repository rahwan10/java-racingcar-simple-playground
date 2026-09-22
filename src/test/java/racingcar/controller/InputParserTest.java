package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Car;

class InputParserTest {

    private final InputParser inputParser = new InputParser();

    @Test
    void 쉼표로_구분한_자동차_이름을_자동차_목록으로_변환한다() {
        List<Car> cars = inputParser.parseCars("pobi, crong");

        assertThat(cars).extracting(Car::getName)
                .containsExactly("pobi", "crong");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,", ",", "pobi,,crong"})
    void 비어_있는_자동차_이름은_예외를_발생시킨다(String inputNames) {
        assertThatThrownBy(() -> inputParser.parseCars(inputNames))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("자동차 이름은 1자 이상 5자 이하여야 합니다.");
    }

    @Test
    void 시도_횟수_문자열을_정수로_변환한다() {
        int tryCount = inputParser.parseTryCount("3");

        assertThat(tryCount).isEqualTo(3);
    }

    @Test
    void 숫자가_아닌_시도_횟수는_예외를_발생시킨다() {
        assertThatThrownBy(() -> inputParser.parseTryCount("three"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void 시도_횟수는_1_이상이어야_한다(String inputTryCount) {
        assertThatThrownBy(() -> inputParser.parseTryCount(inputTryCount))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다.");
    }
}
