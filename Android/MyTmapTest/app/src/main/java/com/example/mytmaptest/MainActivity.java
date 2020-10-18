package com.example.mytmaptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout linMapView;
    Button btnZoomIn;
    Button btnZoomOut;
    Button btnMyLocation;
    Button btnSearch;
    EditText edtSearch;
    TMapView tMapView;
    TMapData tMapData;
    BitmapFactory.Options options;
    Bitmap rightButton;
    AlertDialog dialog;
    String[] array;
    ArrayAdapter<String> adapter;


    ArrayList<TMapPOIItem> poiResult;
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initInstance();
        eventListener();
    }

    private void initView() {
        linMapView = findViewById(R.id.linMapView);
        btnZoomIn = findViewById(R.id.btnZoomIn);
        btnZoomOut = findViewById(R.id.btnZoomOut);
        btnMyLocation = findViewById(R.id.btnMyLocation);
        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        rightButton = BitmapFactory.decodeResource(getResources(), R.drawable.right_arrow, options);


    }

    private void initInstance() {
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xx5a054a6281a64ca08c245a69c45a7603");
        linMapView.addView(tMapView);
        tMapData = new TMapData();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        poiResult = new ArrayList<>();

    }

    private void eventListener() {
        btnSearch.setOnClickListener(listener);
        btnZoomOut.setOnClickListener(listener);
        btnZoomIn.setOnClickListener(listener);
        btnMyLocation.setOnClickListener(listener);

    }
    //리스너 객체화
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnSearch:
                    //POI검색
                    String strData = edtSearch.getText().toString();
                    if (!strData.equals("")) {
                        searchPOI(strData);
                        array = new String[poiResult.size()];
                        for(int i = 0; i < poiResult.size(); i++){
                            Log.d("Test", poiResult.get(i).getPOIName());
                            array[i] = poiResult.get(i).getPOIName();
                        }
                        for(int i = 0; i < poiResult.size(); i++){
                            Log.d("check", array[i]);
                        }

                        createDialog(poiNameList(array));
                        dialog.show();

                    } else
                        Toast.makeText(getApplicationContext(), "검색어를 입력하세요!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnZoomIn:
                    tMapView.MapZoomIn();
                    break;
                case R.id.btnZoomOut:
                    tMapView.MapZoomOut();
                    break;
                case R.id.btnMyLocation:
                    try {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                    } catch (SecurityException e) {

                    }
                    break;
            }
            ;
        }
//위도,경도 수신자 객체화
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();


                tMapView.setCenterPoint(lon, lat);
                tMapView.setLocationPoint(lon, lat);
                tMapView.setIconVisibility(true);



            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        };

    };


    //통합 검색 메서드
    private void searchPOI(String strData) {
        tMapData.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(final ArrayList<TMapPOIItem> arrayList) {
                poiResult .addAll(arrayList);

                for(int i = 0; i < arrayList.size(); i++){
                    TMapPOIItem item = (TMapPOIItem) arrayList.get(i);
                    Log.d("POI Name : ", item.getPOIName().toString() + "," +
                            "Address : " + item.getPOIAddress().replace("null", "") +
                            "point : " + item.getPOIPoint().toString() + "Contents : " + item.getPOIContent());

                    TMapMarkerItem markerItem = new TMapMarkerItem();
                    markerItem.setTMapPoint(item.getPOIPoint());
                    markerItem.setCalloutTitle(item.getPOIName());
                    markerItem.setCalloutSubTitle(item.getPOIAddress());
                    markerItem.setCanShowCallout(true);
                    markerItem.setCalloutRightButtonImage(rightButton);
                }

            }

        });
    }

    private  void createDialog(final ArrayAdapter<String> adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("검색 결과");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strFindItem = adapter.getItem(which);

                Toast.makeText(MainActivity.this, "주소 : " + strFindItem + "" + which,Toast.LENGTH_SHORT).show();

                final TMapPoint tMapPointStart = new TMapPoint(37.5034,126.7660);
                final TMapPoint tMapPointEnd = poiResult.get(which).getPOIPoint();
                Log.d("Test", tMapPointEnd.toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {


                            TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPointStart,tMapPointEnd);
                            tMapPolyLine.setLineColor(Color.BLUE);
                            tMapPolyLine.setLineWidth(8);

                            tMapView.setCenterPoint(tMapPointStart.getLongitude(), tMapPointStart.getLatitude());
                            tMapView.setLocationPoint(tMapPointStart.getLongitude(), tMapPointEnd.getLatitude());
                            tMapView.addTMapPolyLine("Line1", tMapPolyLine);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
        dialog = builder.create();
    }

    private ArrayAdapter<String> poiNameList(String[] strList) {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
        return adapter;
    }

}
