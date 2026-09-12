package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CarsTest {

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
