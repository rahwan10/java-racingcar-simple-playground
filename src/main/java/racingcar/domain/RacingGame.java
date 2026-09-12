package racingcar.domain;

import java.util.List;

/** 자동차 한 라운드의 경주 진행과 우승자 확인을 관리한다. */
public class RacingGame {

    private final Cars cars;

    public RacingGame(Cars cars) {
        this.cars = cars;
    }

    /** 
     * 자동차들을 움직인다.
     * @param numberGenerator generate()를 호출하면 움직임 판단용 정수를 주는 객체
     */
    public void race(NumberGenerator numberGenerator) {
        cars.move(numberGenerator);
    }

    /** 
     * 우승자를 확인한다.
     * @return 우승자 이름 리스트
     */
    public List<String> findWinners() {
        return cars.findWinners();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
