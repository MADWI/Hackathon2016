package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.edu.zut.mad.hackathon2016.api.RequestListener;
import pl.edu.zut.mad.hackathon2016.model.Entry;
import pl.edu.zut.mad.hackathon2016.model.Entry_Table;
import pl.edu.zut.mad.hackathon2016.model.Orlik;
import pl.edu.zut.mad.hackathon2016.model.Reservation;
import retrofit.RetrofitError;

public class ReservationFragment extends Fragment
        implements RequestListener<List<Reservation>> {

    public static final String TAG = "reservation_fragment";
    private List<String> days;
    private HashMap<String, List<Entry>> hours;
    private List<Reservation> reservations = Collections.emptyList();
    private android.widget.ExpandableListAdapter adapter;
    private Orlik orlik;

    @Bind(R.id.exp_list)
    ExpandableListView expandableList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reservation, container, false);
        ButterKnife.bind(this, rootView);

        Bundle args = getArguments();
        if (args != null) {
            orlik = (Orlik) args.getSerializable("orlik");
        }
        DataProvider.getReservation(orlik.getId(), this);

        return rootView;
    }

    private void initList() {
        days = new ArrayList<>();
        hours = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Reservation reservation : reservations) {
            try {
                Date date = dateFormat.parse(reservation.getDay());
                calendar.setTime(date);
                String day = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
                days.add(day);
                List<Entry> entries = reservation.getEntries();
                hours.put(day, entries);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        adapter = new ExpandableListAdapter(getContext(), days, hours);
        expandableList.setAdapter(adapter);
    }

    private String getDayOfWeek(int day) {
        String[] days = new String[] { "Niedziela", "Poniedziałek", "Wtorek", "Sroda", "Czwartek", "Piątek", "Sobota" };
        return days[day - 1];
    }

    @Override
    public void onSuccess(List<Reservation> response) {
        reservations = response;
        initList();
    }

    @Override
    public void onFailure(RetrofitError error) {

    }
}
