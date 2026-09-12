package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {

    @Test
    void 생성한_난수는_0_이상_9_이하다() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        int generatedNumber = randomNumberGenerator.generate();

        assertThat(generatedNumber).isBetween(0, 9);
    }
}
