package com.voidsoul.lbstest;

import static com.amap.api.location.AMapLocationClient.updatePrivacyAgree;
import static com.amap.api.location.AMapLocationClient.updatePrivacyShow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private boolean isFirstLocation = true;
    TextView textView;
    MapView mapView = null;
    AMap aMap;
    public AMapLocationClient mLocationClient = null;
    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if(aMapLocation != null){
                if(aMapLocation.getErrorCode() == 0){

                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append(aMapLocation.getLatitude()+",");
                    currentPosition.append(aMapLocation.getLongitude());
                    currentPosition.append(";");
                    currentPosition.append(aMapLocation.getLocationType());
                    currentPosition.append(";");
                    currentPosition.append(aMapLocation.getAddress());
                    textView.setText(currentPosition.toString());
                    Log.d(TAG, "onLocationChanged: " + currentPosition.toString());
                    LatLng ll = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    final Marker marker = aMap.addMarker(new MarkerOptions().position(ll).title("now").snippet("DefaultMarkder"));
                    if(aMapLocation.getLocationType() == AMapLocation.LOCATION_TYPE_GPS){
                        moveTo(aMapLocation);
                    }
                }
            }
        }
    };
    AMapLocationClientOption mLocationClientOption = new AMapLocationClientOption();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updatePrivacyShow(MainActivity.this, true, true);
        updatePrivacyAgree(MainActivity.this, true);
        textView = (TextView) findViewById(R.id.position_text_view);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        aMap.setMyLocationEnabled(true);
        try {
            mLocationClient = new AMapLocationClient(getApplicationContext());
            mLocationClient.setLocationListener(mLocationListener);
            mLocationClient.setLocationOption(mLocationClientOption);
            mLocationClientOption.setNeedAddress(true);
//            mLocationClient.startLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }else{
            requestLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int result:grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "you must allow all permission", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this, "unknown error", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void moveTo(AMapLocation location){
        if(isFirstLocation){
            isFirstLocation = false;
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(ll, 18, 0, 30));
//            aMap.moveCamera(update);
            aMap.animateCamera(update);
        }

    }

    private void requestLocation(){
        mLocationClient.startLocation();
    }
}