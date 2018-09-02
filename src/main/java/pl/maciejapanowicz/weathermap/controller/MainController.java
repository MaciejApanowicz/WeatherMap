package pl.maciejapanowicz.weathermap.controller;

import pl.maciejapanowicz.weathermap.models.DownloadWeatherService;
import pl.maciejapanowicz.weathermap.views.MainView;
import java.util.Scanner;

public class MainController {
    private MainView mainView;
    private Scanner input;
    private DownloadWeatherService downloadWeatherService = DownloadWeatherService.getINSTANCE();

    public MainController(){
        mainView = new MainView();
        input = new Scanner(System.in);
    }

    public void start(){
        mainView.showWelcomeText();
        createMainLoop();
    }

    public void createMainLoop(){
        String userAnswer;
        do {
            mainView.askForCityToCheckWeather();
            userAnswer = input.nextLine();

            mainView.printWeather(downloadWeatherService.getWeather(userAnswer));
        }while (!userAnswer.equals("exit"));
    }
}

