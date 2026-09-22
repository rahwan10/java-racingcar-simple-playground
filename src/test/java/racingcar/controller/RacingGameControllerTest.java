package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
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
                fixedInputView(), outputView, () -> 4, new InputParser());

        controller.run();

        assertThat(outputView.roundCount).isEqualTo(2);
        assertThat(outputView.winners).containsExactly("pobi", "crong");
    }

    @Test
    void 잘못된_입력이면_오류를_출력하고_다시_입력받는다() {
        RecordingOutputView outputView = new RecordingOutputView();
        RacingGameController controller = new RacingGameController(
                retryingInputView(), outputView, () -> 4, new InputParser());

        controller.run();

        assertThat(outputView.errorMessages).containsExactly(
                "자동차 이름은 1자 이상 5자 이하여야 합니다.",
                "시도 횟수는 1 이상이어야 합니다.");
        assertThat(outputView.roundCount).isEqualTo(2);
    }

    @Test
    void 입력_예외가_아닌_예외는_다시_시도하지_않고_전파한다() {
        InputParser brokenParser = new InputParser() {
            @Override
            public List<Car> parseCars(String inputNames) {
                throw new IllegalArgumentException("입력 처리 외부에서 발생한 오류");
            }
        };
        RacingGameController controller = new RacingGameController(
                fixedInputView(), new RecordingOutputView(), () -> 4, brokenParser);

        assertThatThrownBy(controller::run)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 처리 외부에서 발생한 오류");
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

    private InputView retryingInputView() {
        return new InputView() {
            private int carNameReadCount;
            private int tryCountReadCount;

            @Override
            public String readCarNames() {
                if (carNameReadCount++ == 0) {
                    return "racing";
                }
                return "pobi,crong";
            }

            @Override
            public String readTryCount() {
                if (tryCountReadCount++ == 0) {
                    return "0";
                }
                return "2";
            }
        };
    }

    private static class RecordingOutputView extends OutputView {

        private int roundCount;
        private List<String> winners;
        private final List<String> errorMessages = new ArrayList<>();

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

        @Override
        public void printError(String errorMessage) {
            errorMessages.add(errorMessage);
        }
    }
}
