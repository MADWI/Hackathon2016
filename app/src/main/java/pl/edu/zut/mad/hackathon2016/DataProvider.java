package pl.edu.zut.mad.hackathon2016;


import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.api.RequestCallback;
import pl.edu.zut.mad.hackathon2016.api.RequestListener;
import pl.edu.zut.mad.hackathon2016.api.RestClientManager;
import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Orlik_Table;
import pl.edu.zut.mad.hackathon2016.model.Reservation;

public class DataProvider {

    public static void getOrliks(final RequestListener<List<Orlik>> listener) {
        new Select()
                .from(Orlik.class)
                .async()
                .queryResultCallback(new QueryTransaction.QueryResultCallback<Orlik>() {
                    @Override
                    public void onQueryResult(QueryTransaction transaction, @NonNull CursorResult<Orlik> tResult) {
                        List<Orlik> orliks = tResult.toList();
                        if (orliks == null || orliks.isEmpty()) {
                            RestClientManager.getAllOrliks(new RequestCallback<>(listener));
                        } else {
                            listener.onSuccess(tResult.toList());
                        }
                    }
                }).execute();
    }

    public static List<Orlik> getFavouritesOrliks() {
        return new Select()
                .from(Orlik.class)
                .where(Condition.column(Orlik_Table.isFavourite.getNameAlias()).eq(true))
                .queryList();
    }

    public static void getReservation(final RequestListener<List<Reservation>> listener) {
        new Select()
                .from(Reservation.class)
                .async()
                .queryResultCallback(new QueryTransaction.QueryResultCallback<Reservation>() {
                    @Override
                    public void onQueryResult(QueryTransaction transaction, @NonNull CursorResult<Reservation> tResult) {
                        List<Reservation> reservations = tResult.toList();
                        if (reservations == null || reservations.isEmpty()) {
                            RestClientManager.getAllReservations(new RequestCallback<>(listener));
                        } else {
                            listener.onSuccess(tResult.toList());
                        }
                    }
                }).execute();
    }
}
