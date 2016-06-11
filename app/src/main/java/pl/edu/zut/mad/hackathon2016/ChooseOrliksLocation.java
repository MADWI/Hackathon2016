package pl.edu.zut.mad.hackathon2016;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
        chooseProvince.setOnItemSelectedListener(this);
        chooseTownView.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.ok_button)
    public void onClick() {
        SaveManager saveManager = new SaveManager(getContext());
        saveManager.setLocalizationChoose(true);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment oldChooseLocationFragment = fragmentManager.findFragmentByTag(ChooseOrliksLocation.TAG);
        if (oldChooseLocationFragment != null) {
            fragmentManager.beginTransaction().remove(oldChooseLocationFragment).commit();
        }
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
