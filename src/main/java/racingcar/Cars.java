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

    public void moveAll(List<Integer> randomValues){
        for (int i=0;i<cars.size();i++){
            cars.get(i).move(randomValues.get(i));
        }
    }

    private int findMaxPosition(){
        int maxPosition=0;
        for (Car car:cars){
            if (car.getPosition()>maxPosition){
                maxPosition=car.getPosition();
            }
        }
        return maxPosition;
    }

    public List<String> findWinners(){
        int maxPosition=findMaxPosition();
        List<String> winners = new ArrayList<>();
        for (Car car:cars){
            if(car.getPosition()==maxPosition){
                winners.add(car.getName());
            }
        }
        return winners;
    }
}
