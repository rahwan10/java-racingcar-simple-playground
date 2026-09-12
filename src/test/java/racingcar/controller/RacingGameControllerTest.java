package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.view.InputView;
import racingcar.view.OutputView;

class RacingGameControllerTest {

    @Test
    void 입력한_횟수만큼_라운드를_진행하고_우승자를_출력한다() {
        RecordingOutputView outputView = new RecordingOutputView();
        RacingGameController controller = new RacingGameController(
                fixedInputView(), outputView, () -> 4, new InputValidator());

        controller.run();

        assertThat(outputView.roundCount).isEqualTo(2);
        assertThat(outputView.winners).containsExactly("pobi", "crong");
    }

    private InputView fixedInputView() {
        return new InputView() {
            @Override
            public String readCarNames() {
                return "pobi,crong";
            }

            @Override
            public String readTryCount() {
                return "2";
            }
        };
    }

    private static class RecordingOutputView extends OutputView {

        private int roundCount;
        private List<String> winners;

        @Override
        public void printExecutionResult() {
        }

        @Override
        public void printRound(List<Car> cars) {
            roundCount++;
        }

        @Override
        public void printWinners(List<String> winners) {
            this.winners = winners;
        }
    }
}
