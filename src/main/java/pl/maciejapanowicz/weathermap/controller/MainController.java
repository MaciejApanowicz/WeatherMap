package pl.maciejapanowicz.weathermap.controller;

import pl.maciejapanowicz.weathermap.models.DownloadWeatherService;
import pl.maciejapanowicz.weathermap.views.MainView;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    private void createMainLoop(){
        String userAnswer;
        do {
            mainView.askForCityToCheckWeather();
            userAnswer = input.nextLine();
            if ((!Pattern.matches("[A-ZŻŹĆĄĘŃŚÓ][a-zżźćąńśęó]{2,20}",userAnswer))&&(!userAnswer.equals("exit"))) {
                mainView.typeCorrectCity();
                continue;
            }
                if (userAnswer.equals("exit"))
                break;
            mainView.printWeather(downloadWeatherService.getWeather(userAnswer));
        }while (!userAnswer.equals("exit"));
    }
}

