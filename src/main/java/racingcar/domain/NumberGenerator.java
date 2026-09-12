package racingcar.domain;

// "숫자를 하나 만들어 낼 수 있는 객체"가 지켜야 하는 약속(타입)이다.
// TypeScript의 interface NumberGenerator { generate(): number }와 같은 역할이다.
@FunctionalInterface
public interface NumberGenerator {
    /**
     * 난수를 생성하는 메서드
     * @return 생성된 난수
     */
    int generate();
}
