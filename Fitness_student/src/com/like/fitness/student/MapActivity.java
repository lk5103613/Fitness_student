package com.like.fitness.student;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

public class MapActivity extends Activity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            System.out.println("---------------------->" + bdLocation.getLongitude() + "     " + bdLocation.getLatitude());
            mBaiduMap = mMapView.getMap();
            showMap(bdLocation.getLatitude(), bdLocation.getLongitude());
            mLocationClient.stop();
            BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    System.out.println("-------------------> click");
                    showMap(latLng.latitude, latLng.longitude);
                    MapActivity.this.finish();
                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
                    return false;
                }
            };
            mBaiduMap.setOnMapClickListener(listener);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.bmapView);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();
    }

    private void showMap(double latitude, double longtitude) {
        LatLng llA = new LatLng(latitude, longtitude);
        CoordinateConverter converter= new CoordinateConverter();
        converter.coord(llA);
        OverlayOptions ooA = new MarkerOptions().position(llA).icon(BitmapDescriptorFactory
                .fromResource(R.drawable.point))
                .zIndex(4).draggable(true);
        mBaiduMap.addOverlay(ooA);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(llA, 17.0f);
        mBaiduMap.animateMapStatus(u);
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可�?，默认高精度，设置定位模式，高精度，低功耗，仅设�?
        option.setCoorType("bd09ll");//可�?，默认gcj02，设置返回的定位结果坐标�?
        int span=1000;
        option.setScanSpan(0);//可�?，默�?，即仅定位一次，设置发起定位请求的间隔需要大于等�?000ms才是有效�?
        option.setIsNeedAddress(false);//可�?，设置是否需要地�?��息，默认不需�?
        option.setOpenGps(true);//可�?，默认false,设置是否使用gps
        option.setLocationNotify(false);//可�?，默认false，设置是否当gps有效时按�?S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(false);//可�?，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于�?在北京天安门附近�?
        option.setIsNeedLocationPoiList(false);//可�?，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得�?
        option.setIgnoreKillProcess(false);//可�?，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认�?��
        option.SetIgnoreCacheException(false);//可�?，默认false，设置是否收集CRASH信息，默认收�?
        option.setEnableSimulateGps(false);//可�?，默认false，设置是否需要过滤gps仿真结果，默认需�?
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

}
