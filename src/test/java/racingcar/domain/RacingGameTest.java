package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @Test
    void 한_번의_경주에서_모든_자동차의_이동을_진행한다() {
        Car pobi = new Car("pobi");
        Car crong = new Car("crong");
        RacingGame racingGame = new RacingGame(new Cars(List.of(pobi, crong)));

        racingGame.race(() -> 4);

        assertThat(pobi.getPosition()).isEqualTo(1);
        assertThat(crong.getPosition()).isEqualTo(1);
    }
}
