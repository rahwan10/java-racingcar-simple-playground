package racingcar;

public class Car {
    private static final int MOVABLE_THRESHOLD=4;

    private final String name;
    private int position;

    public Car(String name){
        this.name=name;
        this.position=0;
    }

    public void move(int randomValue){
        if (randomValue>=MOVABLE_THRESHOLD){
            position++;
        }
    }

    public String getName(){
        return name;
    }

    public int getPosition(){
        return position;
    }

}