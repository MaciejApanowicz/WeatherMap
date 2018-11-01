package pl.maciejapanowicz.weathermap.models;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    public String getCurrentWeather(String cityName){
        String url = Config.URL_TO_CURRENT_WEATHER + cityName+"&appid=" +Config.API_KEY;
        String cleanJSON = readWebsite(url);
        JSONObject root = new JSONObject(cleanJSON);

        JSONObject main = root.getJSONObject("main");
        JSONObject sys = root.getJSONObject("sys");

        JSONArray weather = root.getJSONArray("weather");

        String description = "";
        for (int i = 0; i <weather.length();i++){
            JSONObject objectInArray = weather.getJSONObject(i);
            description = objectInArray.getString("description");
        }

        int visibility = root.getInt("visibility");
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
               "humidity: " + humidity + " %" + '\n' +
               "description: " + description + '\n' +
               "visibility: " + visibility + '\n';
    }

    public List<SingleDayWeather> getWeatherForecast(String cityName){
        String url = Config.URL_WEATHER_FORECAST + cityName +"&appid="+Config.API_KEY;
        String cleanJSON = readWebsite(url);
        JSONObject root = new JSONObject(cleanJSON);

        JSONArray list = root.getJSONArray("list");
        //System.out.println(list.length());

        List<SingleDayWeather> seprateDaysWeatherParamiters = new ArrayList<>();

        int localDay = 0;
        int tempSum = 0;
        int counter = 1;

        for (int i = 0; i < list.length(); i++){
            JSONObject objectInsideArray = list.getJSONObject(i);
            JSONObject main = objectInsideArray.getJSONObject("main");

            LocalDateTime localDateTime =
                    LocalDateTime.ofEpochSecond(objectInsideArray.getInt("dt"),0,ZoneOffset.UTC);

            int temp = main.getInt("temp");

            if (localDay != localDateTime.getDayOfYear()){
                seprateDaysWeatherParamiters.add(new SingleDayWeather(localDateTime.minusDays(1),(tempSum/counter)));
                localDay = localDateTime.getDayOfYear();

                tempSum = temp;
                counter = 1;
                //System.out.println("counter = "+ counter);
            }
            else {
                counter++;
                tempSum += temp;
            }
            //System.out.println("temperature: " +(temp -273)+"°C");
        }
        return seprateDaysWeatherParamiters;
    }
}
