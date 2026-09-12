package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

/** 입력을 받아 경주를 진행하고, 각 라운드 결과를 화면에 전달한다. */
public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public RacingGameController(InputView inputView, OutputView outputView,
                                NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    /** 
     * 
     * 경주를 진행하고, 각 라운드 결과를 화면에 전달한다.
     * 또한, 경주가 끝난 후 우승자를 화면에 전달한다.
     * 
     * 1. 자동차 이름을 입력받아 게임 생성
     * 2. 몇 번 시도할지 입력받기
     * 3. “실행 결과” 출력
     * 4. 입력한 횟수만큼 경주와 결과 출력을 반복
     * 5. 우승자 출력
     */
    public void run() {
        /** 자동차들 이름을 받고 RacingGame 리스트 객체를 만듬 */
        RacingGame racingGame = createRacingGame();

        /** 사용자로부터 시도 횟수를 입력받음. */
        int tryCount = inputView.readTryCount();

        /** 경주 시작 전 안내 문구 출력 */
        outputView.printExecutionResult();

        /** racingGame.race로 입력받은 횟수만큼 경주를 진행하고, 
         * 라운드 결과를 출력 
         */
        playRounds(racingGame, tryCount);

        outputView.printWinners(racingGame.findWinners());
    }
    // mvc 패턴의 Controller 역할을 수행하므로 이 코드가 핵심, 이 함수의 흐름만 파악하면 됨

    /** 
     * 입력받은 자동차 이름으로 RacingGame 객체를 생성한다.
     * @return 생성된 RacingGame 객체
     */
    private RacingGame createRacingGame() {
        List<Car> cars = inputView.readCarNames().stream()
                .map(Car::new)//메서드 참조 문법: 객체를 만듬
                .toList();
        return new RacingGame(new Cars(cars));
    }//자동차들 입력받아서 oop로 RacingGame 객체를 만들어서 반환합니다.

    private void playRounds(RacingGame racingGame, int tryCount) {
        for (int count = 0; count < tryCount; count++) {
            racingGame.race(numberGenerator);
            outputView.printRound(racingGame.getCars());
        }
    }
}
