package com.demo.jinx.finalproject.ui.me.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.demo.jinx.finalproject.R;

import kotlin.Suppress;

public class MapFragment extends Fragment {


    private static final int REQUEST_CODE=0x1001;
    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    private PoiSearch poiSearch;
    private LatLng latLng;

    public class MyPoiOverlay extends PoiOverlay {

        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            if(getPoiResult().getAllPoi() != null){
                PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
                if(poiInfo != null){
                    Toast.makeText(getContext(), "名称:"+poiInfo.getName()+
                            "\n地址:"+poiInfo.getAddress()+
                            "\n电话:"+poiInfo.phoneNum, Toast.LENGTH_LONG).show();
                }
            }
            return super.onPoiClick(i);
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation == null || mapView == null){
                return;
            }
            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(bdLocation.getDirection())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            baiduMap.setMyLocationData(locationData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map,container,false);
        mapView = root.findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        baiduMap.setMyLocationEnabled(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        &&root.getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
//            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd0911");
        option.setScanSpan(1000);
        try {
            locationClient = new LocationClient(this.requireContext(),option);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        locationClient.setLocOption(option);

        MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);;

        locationClient.start();

        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.COMPASS,true,null
        );
        baiduMap.setMyLocationConfiguration(myLocationConfiguration);

        poiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener onGetPoiSearchResultListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if(poiResult.error == SearchResult.ERRORNO.NO_ERROR){
                    baiduMap.clear();

                    MyPoiOverlay poiOverlay = new MyPoiOverlay(baiduMap);

                    poiOverlay.setData(poiResult);

                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                    baiduMap.setOnMarkerClickListener(poiOverlay);
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(onGetPoiSearchResultListener);
        EditText editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(v->{
            String keyword = editText.getText().toString();
            poiSearch.searchNearby(new PoiNearbySearchOption()
                    .location(latLng)
                    .radius(10000)
                    .keyword(keyword));
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                locationClient.restart();
            }else{
                Toast.makeText(getContext(), "你拒绝了GPS定位权限，无法定位", Toast.LENGTH_SHORT).show();
            }
        }
    }
}