package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {

    private static final String MOVE_MARK = "-";

    public void printExecutionResult() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printRound(List<Car> cars) {
        cars.forEach(this::printPosition);
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println(String.join(", ", winners) + "가 최종 우승했습니다.");
    }

    public void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    private void printPosition(Car car) {
        System.out.println(car.getName() + " : " + MOVE_MARK.repeat(car.getPosition()));
    }
}
