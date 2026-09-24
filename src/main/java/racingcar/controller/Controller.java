package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.CarsFactory;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.GameSettings;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameSettings gameSettings = inputView.readGameSettings();

        List<Car> carss = CarsFactory.createCars(gameSettings.namesInput());
        Cars cars = new Cars(carss);

        for (int i = 0; i < gameSettings.tryCount(); i++) {
            cars.playRound(new RandomNumberGenerator());
            outputView.printCarsPosition(cars.getCars());
        }

        List<Car> winners = cars.findWinners();
        outputView.printWinner(winners);

    }


}
