package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "racing"})
    void 자동차_이름은_1자_이상_5자_이하여야_한다(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void 자동차는_4_이상의_수를_받으면_전진한다(int number) {
        Car car = new Car("pobi");

        // () -> number는 NumberGenerator의 generate()가 number를 반환하는 테스트용 구현이다.
        car.move(() -> number);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void 자동차는_3_이하의_수를_받으면_멈춘다(int number) {
        Car car = new Car("pobi");

        // 실제 난수 대신 이 테스트가 정한 값을 전달해 항상 같은 결과를 검증한다.
        car.move(() -> number);

        assertThat(car.getPosition()).isZero();
    }
}
