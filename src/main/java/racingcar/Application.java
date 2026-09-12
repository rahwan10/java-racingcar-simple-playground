package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        RacingGameController controller = new RacingGameController(
                new InputView(), new OutputView(), new RandomNumberGenerator());
                
        controller.run();
    }
}
