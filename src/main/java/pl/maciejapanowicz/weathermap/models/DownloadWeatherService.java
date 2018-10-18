package pl.maciejapanowicz.weathermap.models;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadWeatherService{
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
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");
        JSONObject sys = root.getJSONObject("sys");

        JSONArray jsonArray = new JSONArray();


        int temp = main.getInt("temp");
        int pressure = main.getInt("pressure");
        int humidity = main.getInt("humidity");
        int temp_min = main.getInt("temp_min");
        int temp_max = main.getInt("temp_max");
        String country = sys.getString("country");

        return "Country: " + country + '\n' +
               "temperature: " + (temp - 273) + "°C" + '\n' +
               "min/max: " + (temp_min-273) + "°C " + '/' +
               (temp_max-273) + "°C" + '\n' +
               "pressure: " + pressure + " hPa" + '\n' +
               "humidity: " + humidity + " %" + '\n' ;
    }
}
