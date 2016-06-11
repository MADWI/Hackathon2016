package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.edu.zut.mad.hackathon2016.model.Reservation;

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

        initList();

        android.widget.ExpandableListAdapter adapter = new ExpandableListAdapter(getContext(), days, hours);
        expandableList.setAdapter(adapter);

        return rootView;
    }

    private void initList() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = dateFormat.parse();

        days = new ArrayList<>();
        hours = new HashMap<>();
    }
}
