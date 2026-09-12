package racingcar.domain;

import java.util.Random;

// implements는 "NumberGenerator가 정한 generate() 약속을 구현한다"는 의미다.
public class RandomNumberGenerator implements NumberGenerator {

    private static final int RANDOM_BOUND = 10;
    private final Random random = new Random();

    @Override
    public int generate() {
        // nextInt(10)은 0 이상 10 미만, 즉 0부터 9까지의 정수를 반환한다.
        return random.nextInt(RANDOM_BOUND);
    }
}
