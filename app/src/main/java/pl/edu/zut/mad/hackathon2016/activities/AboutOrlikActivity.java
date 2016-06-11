package pl.edu.zut.mad.hackathon2016.activities;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pl.edu.zut.mad.hackathon2016.R;
import pl.edu.zut.mad.hackathon2016.api.RequestCallback;
import pl.edu.zut.mad.hackathon2016.api.RequestListener;
import pl.edu.zut.mad.hackathon2016.api.RestClientManager;
import pl.edu.zut.mad.hackathon2016.model.Orlik;
import retrofit.RetrofitError;


public class AboutOrlikActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment fragment;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_orlik);

        initMap();

        context = this;
    }

    private void initMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.fragment_map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng defaultLocation = new LatLng(53.4544209, 14.531572);
        googleMap.addMarker(new MarkerOptions().position(defaultLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 13));
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng point = null;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            point = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return point;
    }
}
