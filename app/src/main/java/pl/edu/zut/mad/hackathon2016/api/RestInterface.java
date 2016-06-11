package pl.edu.zut.mad.hackathon2016.api;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Reservation;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RestInterface {
    @GET("/getreservations.php")
    void getAllReservations(Callback<List<Reservation>> cb);

    @GET("/getorliks.php")
    void getAllOrliks(Callback<List<Orlik>> cb);

    @POST("/postreserve.php")
    void sendReservation(@Field("id_orlika") String idOrlika,
                         @Field("reservedTime") String reservedTime,
                         @Field("rezerwujacy") String rezerwujacy,
                         Callback callback);
}

