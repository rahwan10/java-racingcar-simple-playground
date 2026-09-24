package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void playRound(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            int number = numberGenerator.generate();
            car.move(number);
        }
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        List<Car> winners = new ArrayList<>();
        for (Car car : cars) {
            if (maxPosition == car.getPosition()) {
                winners.add(car);
            }
        }
        return winners;
    }

    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            if (maxPosition < car.getPosition()) {
                maxPosition = car.getPosition();
            }
        }
        return maxPosition;
    }


}
