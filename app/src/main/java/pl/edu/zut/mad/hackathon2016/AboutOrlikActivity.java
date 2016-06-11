package pl.edu.zut.mad.hackathon2016;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class AboutOrlikActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_orlik);

        initMap();
    }

    private void initMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.fragment_map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
