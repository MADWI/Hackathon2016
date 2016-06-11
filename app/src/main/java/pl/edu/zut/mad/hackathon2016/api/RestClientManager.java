package pl.edu.zut.mad.hackathon2016.api;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Reservation;
import pl.edu.zut.mad.hackathon2016.model.Weather;
import retrofit.Callback;

public class RestClientManager {
    public static final String BASE_URL = "http://bm29640.zut.edu.pl/orl_api/";
    public static final String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    public static RestClient client = new RestClient(BASE_URL);
    public static RestClient clientWeather = new RestClient(BASE_WEATHER_URL);
    public static RestInterface getRestApi(){
        return client.getService();
    }

    public static void getAllReservations(Callback<List<Reservation>> callback){
        RestInterface restInterface = getRestApi();
        restInterface.getAllReservations(callback);
    }

    public static void getAllOrliks(Callback<List<Orlik>> callback){
        RestInterface restInterface = getRestApi();
        restInterface.getAllOrliks(callback);
    }

    public static void sendReservation(int idOrlika, String reservedTime, String rezerwujacy, Callback<Object> callback) {
        RestInterface restInterface = getRestApi();
        restInterface.sendReservation(idOrlika, reservedTime, rezerwujacy, callback);
    }

    public static void getWeather(Callback<Weather> callback){
        RestInterface restInterface = clientWeather.getService();
        restInterface.getWeather(callback);
    }
}
