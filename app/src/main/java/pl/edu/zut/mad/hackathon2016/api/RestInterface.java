package pl.edu.zut.mad.hackathon2016.api;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Reservation;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RestInterface {
    @GET("/getreservations.php")
    void getAllReservations(Callback<List<Reservation>> cb);

    @GET("/getorliks.php")
    void getAllOrliks(Callback<List<Orlik>> cb);

    @GET("/forecast?id=3083829&appid=61d080d3689356177500372776203b69&units=metric")
    void getWeather(Callback<Weather> cb);

    @FormUrlEncoded
    @POST("/postreserve.php")
    void sendReservation(@Field("id_orlika") int idOrlika,
                         @Field("reservedTime") String reservedTime,
                         @Field("rezerwujacy") String rezerwujacy,
                         Callback<Object> callback);
}

