package pl.maciejapanowicz.weathermap.models;

import java.time.LocalDateTime;

public class SingleDayWeather {
    private int temp;
    private LocalDateTime localDateTime;

    public SingleDayWeather (LocalDateTime localDateTime, int temp){
        this.temp = temp;
        this.localDateTime = localDateTime;
    }

    public int getTemp() {
        return temp;
    }

    public LocalDateTime getLocalDateTime(){
        return localDateTime;
    }
}
