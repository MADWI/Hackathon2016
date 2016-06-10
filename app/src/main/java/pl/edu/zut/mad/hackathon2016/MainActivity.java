package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.choose_province)
    Spinner chooseProvince;

    @Bind(R.id.choose_town)
    Spinner chooseTownView;

    @Bind(R.id.choose_orlik)
    Spinner chooseOrlikView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_town_main);
        ButterKnife.bind(this);

        initDropDownLists();
    }

    private void initDropDownLists() {
        List<String> provinces = new ArrayList<>();
        provinces.add("śląskie");
        provinces.add("wielkopolskie");

        List<String> towns = new ArrayList<>();
        towns.add("Warszawa");
        towns.add("Szczecin");

        List<String> orliks = new ArrayList<>();
        orliks.add("Orlik 1");
        orliks.add("Orlik 2");

        ArrayAdapter<String> provincesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinces);
        ArrayAdapter<String> townsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, towns);
        ArrayAdapter<String> orliksAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, orliks);

        chooseProvince.setAdapter(provincesAdapter);
        chooseTownView.setAdapter(townsAdapter);
        chooseOrlikView.setAdapter(orliksAdapter);

        chooseProvince.setOnItemSelectedListener(this);
        chooseTownView.setOnItemSelectedListener(this);
        chooseOrlikView.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.choose_province:
                break;
            case R.id.choose_town:
                break;
            case R.id.choose_orlik:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
