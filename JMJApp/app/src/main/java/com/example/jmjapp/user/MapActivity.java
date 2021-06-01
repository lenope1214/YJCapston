package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.jmjapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mgoogleMap;
    private String shopName = ShopDetailActivity.shopName;
    private String shopAddress = ShopDetailActivity.shopAddress;
    private String shopDetailAddress = ShopDetailActivity.shopDetailAddress;
    private String Address = shopAddress+shopDetailAddress;

    private ImageView imageView20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mgoogleMap = googleMap;
        Context context = this;

//        final LatLng Pochen = new LatLng(37.894936, 127.200344);

        Location citiHallLocation = addToPoint(context);
        final LatLng shop = new LatLng(citiHallLocation.getLatitude(), citiHallLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(shop);
        markerOptions.title(shopName);
        googleMap.addMarker(markerOptions);

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback(){
            @Override
            public void onMapLoaded() {
                mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(shop));
                mgoogleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
            }
        }); //구글맵 로딩 완료 시 카메라 위치 조정
    }

    private Location addToPoint(Context context) {
        Location location = new Location(shopAddress);
        System.out.println(shopAddress+"tttttttttttt");
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(shopAddress,3);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null) {
            for (int i=0; i<addresses.size(); i++){
                Address lating = addresses.get(i);
                location.setLatitude(lating.getLatitude());
                location.setLongitude(lating.getLongitude());
            }
        }
        return location;
    }
}