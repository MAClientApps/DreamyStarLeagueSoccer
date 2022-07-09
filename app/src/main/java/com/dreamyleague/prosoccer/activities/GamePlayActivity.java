package com.dreamyleague.prosoccer.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dreamyleague.prosoccer.R;
import com.dreamyleague.prosoccer.utility.ViewAdMob;
import com.unity3d.ads.BuildConfig;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;


public class GamePlayActivity extends AppCompatActivity {

    private android.webkit.WebView Content_Web;
    private String link = null;

    // LinearLayout layoutAdsBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);
        // layoutAdsBanner=findViewById(R.id.bannerAds_2);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {

        }
        readExtraData();
        initGameWebView();
        if (ViewAdMob.UNITY_IS_VISIBLE && ViewAdMob.isConnectionAvailable(GamePlayActivity.this)) {
           // UnityAds.addListener(this);
            if (!UnityAds.isInitialized()) {
                UnityAds.initialize(GamePlayActivity.this, ViewAdMob.UNITY_GAME_ID, BuildConfig.DEBUG);
            }
        }
    }
//    private void show_Banner() {
//        if (UnityAds.isReady(ViewAdMob.UNITY_BANNER_ID)) {
//            ViewAdMob.showBannerAds(GamePlayActivity.this, layoutAdsBanner);
//        } else {
//            layoutAdsBanner.setVisibility(View.GONE);
//        }
//    }


  //  @Override
//    public void onUnityAdsReady(String s) {
//        show_Banner();
//    }

    private void initGameWebView() {
        Content_Web = (android.webkit.WebView)findViewById(R.id.wv_allgame);
        CookieManager.getInstance().setAcceptCookie(true);
        Content_Web.getSettings().setJavaScriptEnabled(true);
        Content_Web.getSettings().setUseWideViewPort(true);
        Content_Web.getSettings().setLoadWithOverviewMode(true);
        Content_Web.getSettings().setDomStorageEnabled(true);
        Content_Web.getSettings().setPluginState(WebSettings.PluginState.ON);
        Content_Web.setWebChromeClient(new WebChromeClient());
        Content_Web.setVisibility(View.VISIBLE);
        Content_Web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {

                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        Content_Web.loadUrl(link);
    }

    private void readExtraData() {
        if(getIntent().hasExtra("link") && getIntent().getExtras().getString("link")!=null){
            link =getIntent().getExtras().getString("link");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Content_Web.onResume();
       // UnityAds.addListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Content_Web.onPause();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (Content_Web.canGoBack()) {
                        Content_Web.goBack();
                    } else {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Content_Web.loadUrl("about:blank");
    }



}