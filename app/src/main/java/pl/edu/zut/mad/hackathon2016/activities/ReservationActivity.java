package pl.edu.zut.mad.hackathon2016.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.ReservationFragment;


public class ReservationActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ReservationFragment fragment = new ReservationFragment();
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservation_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.info) {
            Intent i = new Intent(this, AboutOrlikActivity.class);
            startActivity(i);
        }

        return true;
    }
}
