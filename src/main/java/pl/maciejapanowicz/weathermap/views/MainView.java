package pl.maciejapanowicz.weathermap.views;

import pl.maciejapanowicz.weathermap.controller.MainController;

public class MainView {

    public void showWelcomeText (){
        System.out.println("Welcome User!");
    }

    public void askForCityToCheckWeather(){
        System.out.println("Type the name of the city: ");
    }

    public void printWeather (String weatherText){
        System.out.println(weatherText);
    }
    public void typeCorrectCity (){
        System.out.println("You have typed the city incorrectly, please type the city again");
    }
}
