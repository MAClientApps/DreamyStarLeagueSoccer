package com.dreamyleague.prosoccer.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dreamyleague.prosoccer.R;
import com.dreamyleague.prosoccer.ScrollLayoutManager;
import com.dreamyleague.prosoccer.adapter.GameHomeAdapter;
import com.dreamyleague.prosoccer.adapter.GameHomeSquareAdapter;
import com.dreamyleague.prosoccer.model.BannerClass;
import com.dreamyleague.prosoccer.model.ContentItemClass;
import com.dreamyleague.prosoccer.utility.ViewAdMob;
import com.unity3d.ads.BuildConfig;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IUnityAdsListener {

    RecyclerView  RCV_Game,rv_viewall;
    private String mtypeValue = "Games.json";
    ScrollLayoutManager scroll_LayoutManager;
    private ArrayList<ContentItemClass> gamedata = new ArrayList<>();
    private ArrayList<ContentItemClass> gamedata2 = new ArrayList<>();
    private ArrayList<BannerClass> datalist = new ArrayList<>();
    private ArrayList<BannerClass> datahigh = new ArrayList<>();
    private ArrayList<BannerClass> dataall = new ArrayList<>();
    LinearLayoutManager hLayoutManager;
    GridLayoutManager gLayoutManager;
    GameHomeAdapter Gadapter;
    GameHomeSquareAdapter Aadapter;
    TextView viewall;
    LinearLayout layoutAdsBanner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetGames().execute();
        hLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        hLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_viewall=findViewById(R.id.rv_viewallgame);
        RCV_Game = (RecyclerView) findViewById(R.id.rv_high);
        RCV_Game.setLayoutManager(hLayoutManager);
        gLayoutManager=new GridLayoutManager(MainActivity.this,2,RecyclerView.VERTICAL,false);
        rv_viewall.setLayoutManager(gLayoutManager);
        layoutAdsBanner1=findViewById(R.id.bannerAds_3);
        viewall = findViewById(R.id.viewall);
        if (ViewAdMob.UNITY_IS_VISIBLE && ViewAdMob.isConnectionAvailable(MainActivity.this)) {
            UnityAds.addListener(this);
            if (!UnityAds.isInitialized()) {
                UnityAds.initialize(MainActivity.this, ViewAdMob.UNITY_GAME_ID, BuildConfig.DEBUG);
            } else {
                showBanner();
            }
        }

    }
    private void showBanner() {
        if (UnityAds.isReady(ViewAdMob.UNITY_BANNER_ID)) {
            ViewAdMob.showBannerAds(MainActivity.this, layoutAdsBanner1);
        } else {
            layoutAdsBanner1.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UnityAds.addListener(this);
    }

    @Override
    public void onUnityAdsReady(String s) {
    showBanner();
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

    public class GetGames extends AsyncTask<String, Integer, JSONObject> {

            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    pd = new ProgressDialog(MainActivity.this);
                    pd.show();
                } catch (Exception e) {

                }
            }

            @Override
            protected JSONObject doInBackground(String... strings) {
                try {
                    return ViewAdMob.loadJSONFromAsset(MainActivity.this, mtypeValue);
                } catch (Exception e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(JSONObject jsonGames) {
                super.onPostExecute(jsonGames);
                super.onPostExecute(jsonGames);
                try {

                    try {
                        if (jsonGames != null && jsonGames.has("footballGame") && !jsonGames.isNull("footballGame")) {
                            final JSONArray jsonArrayAction = jsonGames.getJSONArray("footballGame");
                            datalist.addAll(BannerClass.getCategoryList(jsonArrayAction));
                            dataall.addAll(BannerClass.getCategoryList(jsonArrayAction));
                            datahigh.addAll(BannerClass.getCategoryList(jsonArrayAction));
                        }

                            gamedata.addAll(dataall.get(0).getContentItemArrayList());
                            gamedata2.addAll(datahigh.get(1).getContentItemArrayList());

                    } catch (Exception e) {

                    }
                } catch (Exception e) {
                }
                try {
                    if (pd != null && pd.isShowing()) {
                        pd.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadList();
                Aadapter=new GameHomeSquareAdapter(gamedata, MainActivity.this);
                rv_viewall.setAdapter(Aadapter);
                Aadapter.notifyDataSetChanged();
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        private void loadList () {
            Gadapter = new GameHomeAdapter(gamedata2, this);
            RCV_Game.setAdapter(Gadapter);
            Gadapter.notifyDataSetChanged();
        }
}
