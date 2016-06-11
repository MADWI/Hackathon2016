package pl.edu.zut.mad.hackathon2016.api;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Reservation;
import retrofit.Callback;

public class RestClientManager {
    public static RestClient client = new RestClient();

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
}
