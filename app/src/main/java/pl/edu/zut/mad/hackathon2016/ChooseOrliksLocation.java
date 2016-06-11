package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.edu.zut.mad.hackathon2016.activities.MainActivity;

public class ChooseOrliksLocation extends Fragment implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "choose_orliks_location";

    @Bind(R.id.choose_province)
    Spinner chooseProvince;

    @Bind(R.id.choose_town)
    Spinner chooseTownView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_orliks_loc_main, container, false);
        ButterKnife.bind(this, rootView);

        initDropDownLists();

        return rootView;
    }

    private void initDropDownLists() {
        chooseProvince.setOnItemSelectedListener(this);
        chooseTownView.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.ok_button)
    public void onClick() {
        SaveManager saveManager = new SaveManager(getContext());
        saveManager.setLocalizationChoose(true);

        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).checkLocationChoose();
            }
        });*/
        getActivity().recreate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.choose_province:
                break;
            case R.id.choose_town:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
