package pl.maciejapanowicz.weathermap.views;

public class MainView {

    public void showWelcomeText (){
        System.out.println("Welcome User!");
    }

    public void askForCityToCheckWeather(){
        System.out.println("Type the name of the city you want the data to be displayed: \n" +
                "(or \"exit\" if you want to close the app)");
    }

    public void printWeather (String weatherText){
        System.out.println(weatherText);
    }
    public void typeCorrectCity (){
        System.out.println("You have typed the city incorrectly, please type the city again.");
        System.out.println("There should only be letters in the city's name and the first one should be a capital letter.");
    }
}
