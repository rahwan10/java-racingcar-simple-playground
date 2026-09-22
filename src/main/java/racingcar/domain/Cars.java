package racingcar.domain;

import java.util.List;

/** 여러 자동차를 하나의 경주 참가자 목록으로 관리한다. */
public class Cars {

    private static final String EMPTY_CARS_MESSAGE = "참가 자동차는 한 대 이상이어야 합니다.";
    /** 자동차 목록 */
    private final List<Car> cars;

    /** 
     * 자동차 목록을 "생성"한다.
     * 즉, 복사가 아닌, 새롭게 생성된 객체를 반환한다. 
     * 따라서 외부에서 전달받은 List<Car>가 바뀌더라도 Cars 내부의 자동차 목록은 바뀌지 않는다.
     */
    public Cars(List<Car> cars) {
        validateCars(cars);
        this.cars = List.copyOf(cars);
    }

    private void validateCars(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CARS_MESSAGE);
        }
    }

    public void move(NumberGenerator numberGenerator) {
        /** 자동차마다 generate()를 한 번씩 호출해, 각자의 이동 여부를 판단한다. */
        cars.forEach(car -> car.move(numberGenerator));
    }

    /** 
     * 자동차 목록에서 가장 멀리 이동한 자동차를 찾아 이름을 반환한다.
     * @return 가장 멀리 이동한 자동차의 이름 목록
     */
    public List<String> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .toList();
    }

    public List<Car> getCars() {
        return cars;
    }

    /** 
     * 자동차 목록에서 가장 멀리 이동한 자동차의 위치를 반환한다.
     * @return 가장 멀리 이동한 자동차의 위치
     */
    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }
}
