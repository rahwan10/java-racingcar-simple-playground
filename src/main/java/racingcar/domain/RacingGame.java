package racingcar.domain;

import java.util.List;

/** 경주의 횟수와 진행 순서를 관리한다. */
public class RacingGame {

    private final Cars cars;
    private final int tryCount;

    public RacingGame(Cars cars, int tryCount) {
        this.cars = cars;
        this.tryCount = tryCount;
    }

    public void race(NumberGenerator numberGenerator) {
        for (int count = 0; count < tryCount; count++) {
            cars.move(numberGenerator);
        }
    }

    public List<String> findWinners() {
        return cars.findWinners();
    }
}
