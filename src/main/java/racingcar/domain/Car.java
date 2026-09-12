package racingcar.domain;

public class Car {

    //final : 생성 후 변수가 바뀌지 x
    private static final int MOVABLE_NUMBER = 4;

    private final String name;
    private int position;

    //생성자
    public Car(String name) {
        this.name = name;
    }
    
    /**
     * 숫자를 생성하는 객체를 전달받아 자동차를 움직인다.
     *
     * numberGenerator는 "타입 변수명" 형태의 파라미터다.
     * 실제 실행에서는 new RandomNumberGenerator()를, 테스트에서는 () -> 4를 전달할 수 있다.</p>
     *
     * @param numberGenerator generate()를 호출하면 움직임 판단용 정수를 주는 객체
     */
    public void move(NumberGenerator numberGenerator) {
        // 전달받은 객체에게 숫자를 요청한다. Car는 숫자를 만드는 방법을 알 필요가 없다.
        int number = numberGenerator.generate();
        if (canMove(number)) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    private boolean canMove(int number) {
        return number >= MOVABLE_NUMBER;
    }
}
