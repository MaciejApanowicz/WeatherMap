package pl.maciejapanowicz.weathermap.models;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWeatherService {
    private static DownloadWeatherService INSTANCE = new DownloadWeatherService();

    private DownloadWeatherService(){
    }

    public static DownloadWeatherService getINSTANCE(){
        return INSTANCE;
    }

    private String readWebsite (String url){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();

            int response;
            while ((response = inputStream.read()) != -1){
                stringBuilder.append((char) response);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String getWeather(String cityName){
        String url = Config.URL_TO_APi + cityName + "&appid=" +Config.API_KEY;
        String json = readWebsite(url);

        String result;
        JSONObject jsonObject = new JSONObject(json);
        JSONObject main = jsonObject.getJSONObject("main");

        double temp = main.getDouble("temp");
        int pressure = main.getInt("pressure");
        int humidity = main.getInt("humidity");
        double temp_min = main.getDouble("temp_min");
        double temp_max = main.getDouble("temp_max");


        return "Temp: " + (temp - 273) + " pressure: " + pressure + " humidity: " + humidity;
    }
}
