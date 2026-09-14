package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {
    @Test
    @DisplayName("가장 멀리 간 자동차 한 대가 우승한다")
    void findSingleWinner(){
        Cars cars=new Cars(List.of("pobi","jun"));
        cars.moveAll(List.of(4,3));

        assertThat(cars.findWinners()).containsExactly("pobi");
    }
}
