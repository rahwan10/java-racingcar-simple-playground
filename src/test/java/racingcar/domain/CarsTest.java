package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;

class CarsTest {

    @Test
    void 참가_자동차는_한_대_이상이어야_한다() {
        assertThatThrownBy(() -> new Cars(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가 자동차는 한 대 이상이어야 합니다.");
    }

    @Test
    void 모든_자동차는_한_번의_경주에서_이동을_시도한다() {
        Car pobi = new Car("pobi");
        Car crong = new Car("crong");
        Cars cars = new Cars(List.of(pobi, crong));

        cars.move(() -> 4);

        assertThat(pobi.getPosition()).isEqualTo(1);
        assertThat(crong.getPosition()).isEqualTo(1);
    }

    @Test
    void 자동차마다_서로_다른_이동값을_전달받는다() {
        Car pobi = new Car("pobi");
        Car crong = new Car("crong");
        Cars cars = new Cars(List.of(pobi, crong));
        Queue<Integer> generatedNumbers = new ArrayDeque<>(List.of(4, 3));

        cars.move(generatedNumbers::remove);

        assertThat(pobi.getPosition()).isEqualTo(1);
        assertThat(crong.getPosition()).isZero();
        assertThat(generatedNumbers).isEmpty();
    }

    @Test
    void 외부의_원본_목록을_변경해도_참가_자동차_목록은_바뀌지_않는다() {
        List<Car> originalCars = new ArrayList<>(List.of(new Car("pobi")));
        Cars cars = new Cars(originalCars);

        originalCars.add(new Car("crong"));

        assertThat(cars.getCars()).extracting(Car::getName)
                .containsExactly("pobi");
    }

    @Test
    void 외부에서_참가_자동차_목록의_구조를_변경할_수_없다() {
        Cars cars = new Cars(List.of(new Car("pobi")));

        assertThatThrownBy(() -> cars.getCars().add(new Car("crong")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 가장_멀리_간_자동차_한_대를_우승자로_찾는다() {
        Car pobi = new Car("pobi");
        Car crong = new Car("crong");
        Cars cars = new Cars(List.of(pobi, crong));
        pobi.move(() -> 4);

        assertThat(cars.findWinners()).containsExactly("pobi");
    }

    @Test
    void 가장_멀리_간_자동차_여러_대를_공동_우승자로_찾는다() {
        Car pobi = new Car("pobi");
        Car crong = new Car("crong");
        Cars cars = new Cars(List.of(pobi, crong));
        pobi.move(() -> 4);
        crong.move(() -> 4);

        assertThat(cars.findWinners()).containsExactly("pobi", "crong");
    }
}
