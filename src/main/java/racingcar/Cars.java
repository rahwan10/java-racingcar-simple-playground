package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<String> names){
        this.cars=new ArrayList<>();
        for (String name : names){
            cars.add(new Car(name));
        }
    }
}
