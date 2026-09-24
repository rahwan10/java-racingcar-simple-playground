package racingcar.domain;

public record CarName(String value) {
    public CarName {
        if (value.length() > 5) {
            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
        }
    }
}
