package pl.edu.zut.mad.hackathon2016.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import pl.edu.zut.mad.hackathon2016.ChooseOrliksLocation;
import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.SaveManager;
import pl.edu.zut.mad.hackathon2016.SearchHelper;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    SearchHelper searchHelper;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        searchHelper = new SearchHelper();
        searchHelper.setSearchView(this, menu);

        return true;
    }
}
