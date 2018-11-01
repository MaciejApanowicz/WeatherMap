package pl.maciejapanowicz.weathermap.views;

public class MainView {

    public void showWelcomeText (){
        System.out.println("\nWelcome User!");
    }

    public void askForCityToCheckWeather(){
        System.out.println("Type the name of the city: \n" +
                "(or \"exit\" if you want to close the app)");
    }

    public void printWeather (String weatherText){
        System.out.println(weatherText);
    }
    public void typeCorrectCity (){
        System.out.println("You have typed the city incorrectly, please type the city again.");
        System.out.println("There should only be letters in the city's name and the first one should be a capital letter.\n");
    }

    public static void showMenu () {
        System.out.println(" 1 - Current weather");
        System.out.println(" 2 - Weather forecast");
        System.out.println(" 3 - EXIT");
    }
}
