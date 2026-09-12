package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @Test
    void 입력한_횟수만큼_모든_자동차의_경주를_진행한다() {
        Car pobi = new Car("pobi");
        Car crong = new Car("crong");
        RacingGame racingGame = new RacingGame(new Cars(List.of(pobi, crong)), 3);

        racingGame.race(() -> 4);

        assertThat(pobi.getPosition()).isEqualTo(3);
        assertThat(crong.getPosition()).isEqualTo(3);
    }
}
