package pl.maciejapanowicz.weathermap.controller;

import pl.maciejapanowicz.weathermap.models.DownloadWeatherService;
import pl.maciejapanowicz.weathermap.models.SingleDayWeather;
import pl.maciejapanowicz.weathermap.views.MainView;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainController {
    private MainView mainView;
    private Scanner input;
    private DownloadWeatherService downloadWeatherService = DownloadWeatherService.getINSTANCE();

    private final String IS_CITY_VALID_REGEX ="[A-ZŻŹĆĄĘŃŚÓ][a-zżźćąńśęó]{2,20}";

    public MainController(){
        mainView = new MainView();
        input = new Scanner(System.in);
    }

    public void start(){
        mainView.showWelcomeText();
        createMenu();
    }

    private void createMenu() {

        String choose;
        do {
            MainView.showMenu();
            choose = input.nextLine();

            if (choose.equals("1")) {
                mainView.printWeather(downloadWeatherService.getCurrentWeather(setCityToCheck()));

            } else
                if (choose.equals("2")) {
                //todo create logic for getWeatherForecast method;
                    String text = "";
                    for (SingleDayWeather singleDayWeather : downloadWeatherService.getWeatherForecast(setCityToCheck())){
                        text += singleDayWeather.getTemp() + " / " + singleDayWeather.getLocalDateTime() + "\n";
                    }
               mainView.printWeather(text);
            }
        } while (!((choose.equals("3"))||(choose.toUpperCase().equals("EXIT"))));
    }

    private String setCityToCheck(){
        String userAnswer;
        mainView.askForCityToCheckWeather();
        userAnswer = input.nextLine();

        if ((userAnswer.equals("exit"))) System.exit(-1);
        while ((!(Pattern.matches(IS_CITY_VALID_REGEX,userAnswer)))) {
                mainView.typeCorrectCity();
        }
        return userAnswer;
    }
}