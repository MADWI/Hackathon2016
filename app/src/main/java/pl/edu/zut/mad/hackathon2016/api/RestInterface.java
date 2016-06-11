package pl.edu.zut.mad.hackathon2016.api;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Reservation;
import retrofit.Callback;
import retrofit.http.GET;

public interface RestInterface {
    @GET("/getreservations.php")
    void getAllReservations(Callback<List<Reservation>> cb);

    @GET("/getorliks.php")
    void getAllOrliks(Callback<List<Orlik>> cb);
}

