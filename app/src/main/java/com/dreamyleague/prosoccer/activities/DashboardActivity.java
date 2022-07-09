package com.dreamyleague.prosoccer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.dreamyleague.prosoccer.R;
import com.dreamyleague.prosoccer.utility.ViewAdMob;
import com.unity3d.ads.BuildConfig;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;


public class DashboardActivity extends AppCompatActivity implements IUnityAdsListener {

    ImageView btnPlay1;
    LinearLayout layoutAdsBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        layoutAdsBanner=findViewById(R.id.bannerAds_1);
        btnPlay1 = findViewById(R.id.btnPLay);
        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        if (ViewAdMob.UNITY_IS_VISIBLE && ViewAdMob.isConnectionAvailable(DashboardActivity.this)) {
            UnityAds.addListener(this);
            if (!UnityAds.isInitialized()) {
                UnityAds.initialize(DashboardActivity.this, ViewAdMob.UNITY_GAME_ID, BuildConfig.DEBUG);
            } else {
                show_Banner();
            }
        }

    }
    private void show_Banner() {
        if (UnityAds.isReady(ViewAdMob.UNITY_BANNER_ID)) {
            ViewAdMob.showBannerAds(DashboardActivity.this, layoutAdsBanner);
        } else {
            layoutAdsBanner.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UnityAds.addListener(this);
    }

    @Override
    public void onUnityAdsReady(String s) {
        show_Banner();
    }



    @Override
    public void onUnityAdsStart(String s) {

    }

    @Override
    public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {

    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {

    }
}