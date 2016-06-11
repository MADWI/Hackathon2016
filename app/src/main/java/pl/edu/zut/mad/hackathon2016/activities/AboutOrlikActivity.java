package pl.edu.zut.mad.hackathon2016.activities;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

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
        RestClientManager.getAllOrliks(new RequestCallback<>(new RequestListener<List<Orlik>>() {
            @Override
            public void onSuccess(List<Orlik> response) {
                for (Orlik orlik : response) {
                    int commaPosition = orlik.getAdres().indexOf(',');
                    if (commaPosition != -1) {
                        commaPosition = orlik.getAdres().length();
                        String street = orlik.getAdres().substring(0, commaPosition);

                        LatLng address = getLocationFromAddress(context, orlik.getMiasto() + ", " + street);
                        if (address != null) {
                            googleMap.addMarker(new MarkerOptions().position(address));
                        }
                    }
                }
            }

            @Override
            public void onFailure(RetrofitError error) {
            }
        }));
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
