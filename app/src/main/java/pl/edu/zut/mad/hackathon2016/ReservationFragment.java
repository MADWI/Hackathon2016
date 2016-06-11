package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReservationFragment extends Fragment {

    public static final String TAG = "reservation_fragment";
    private List<String> days;
    private HashMap<String, List<String>> hours;

    @Bind(R.id.exp_list)
    ExpandableListView expandableList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reservation, container, false);
        ButterKnife.bind(this, rootView);

        prepareList();

        android.widget.ExpandableListAdapter adapter = new ExpandableListAdapter(getContext(), days, hours);
        expandableList.setAdapter(adapter);

        return rootView;
    }

    private void prepareList() {
        days = new ArrayList<>();
        hours = new HashMap<>();

        days.add("Poniedzialek");
        days.add("Wtorek");
        days.add("Sroda");

        List<String> day1 = new ArrayList<>();
        day1.add("9:30");
        day1.add("11:00");
        day1.add("12:30");
        day1.add("14:00");
        hours.put(days.get(0), day1);
        hours.put(days.get(1), day1);
        hours.put(days.get(2), day1);
    }
}
