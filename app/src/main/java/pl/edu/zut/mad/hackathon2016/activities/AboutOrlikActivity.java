package pl.edu.zut.mad.hackathon2016.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.api.RequestCallback;
import pl.edu.zut.mad.hackathon2016.api.RequestListener;
import pl.edu.zut.mad.hackathon2016.api.RestClientManager;
import pl.edu.zut.mad.hackathon2016.model.Orlik;
import retrofit.RetrofitError;


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
