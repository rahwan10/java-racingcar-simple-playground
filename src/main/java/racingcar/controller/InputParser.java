package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import racingcar.domain.Car;

/** 화면에서 받은 문자열을 도메인에서 사용할 값으로 변환한다. */
public class InputParser {

    private static final int MINIMUM_TRY_COUNT = 1;

    public List<Car> parseCars(String inputNames) {
        return Arrays.stream(inputNames.split(",", -1))
                .map(String::trim)
                .map(this::createCar)
                .toList();
    }

    public int parseTryCount(String inputTryCount) {
        int tryCount = parseInteger(inputTryCount);
        validateTryCount(tryCount);
        return tryCount;
    }

    private int parseInteger(String inputTryCount) {
        try {
            return Integer.parseInt(inputTryCount);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("시도 횟수는 숫자여야 합니다.");
        }
    }

    private void validateTryCount(int tryCount) {
        if (tryCount < MINIMUM_TRY_COUNT) {
            throw new InvalidInputException("시도 횟수는 1 이상이어야 합니다.");
        }
    }

    private Car createCar(String name) {
        try {
            return new Car(name);
        } catch (IllegalArgumentException exception) {
            throw new InvalidInputException(exception.getMessage());
        }
    }
}
