package pl.maciejapanowicz.weathermap.models;

public class DownloadWeatherService {
    private static DownloadWeatherService INSTANCE = new DownloadWeatherService();

    private DownloadWeatherService(){
    }

    public static DownloadWeatherService getINSTANCE(){
        return INSTANCE;
    }
}
