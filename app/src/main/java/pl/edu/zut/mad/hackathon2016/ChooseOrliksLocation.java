package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        List<String> provinces = new ArrayList<>();
        provinces.add("śląskie");
        provinces.add("wielkopolskie");

        List<String> towns = new ArrayList<>();
        towns.add("Warszawa");
        towns.add("Szczecin");

        ArrayAdapter<String> provincesAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, provinces);
        ArrayAdapter<String> townsAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, towns);

        chooseProvince.setAdapter(provincesAdapter);
        chooseTownView.setAdapter(townsAdapter);

        chooseProvince.setOnItemSelectedListener(this);
        chooseTownView.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.ok_button)
    public void onClick() {
        SaveManager saveManager = new SaveManager(getContext());
        saveManager.setLocalizationChoose(true);
        Toast.makeText(getContext(), "Next fragment :)", Toast.LENGTH_SHORT).show();
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
