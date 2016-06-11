package pl.edu.zut.mad.hackathon2016.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.edu.zut.mad.hackathon2016.ChooseOrliksLocation;
import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.SaveManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLocationChoose();
    }

    private void checkLocationChoose() {
        SaveManager saveManager = new SaveManager(this);
        if (!saveManager.isLocalizationChoose()) {
            ChooseOrliksLocation chooseOrliksLocation = new ChooseOrliksLocation();
            getSupportFragmentManager().beginTransaction()
                .add(R.id.main_activity_container, chooseOrliksLocation, ChooseOrliksLocation.TAG)
                .commit();
        } else {
            // TODO: next fragment :)
        }
    }
}
