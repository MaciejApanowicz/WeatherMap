package pl.maciejapanowicz.weathermap.views;

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

}
