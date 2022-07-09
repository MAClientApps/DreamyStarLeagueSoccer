package com.dreamyleague.prosoccer.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BannerClass {
    Integer id;

    ArrayList<ContentItemClass> ContentItemArrayList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ContentItemClass> getContentItemArrayList() {
        return ContentItemArrayList;
    }

    public void setContentItemArrayList(ArrayList<ContentItemClass> ContentArraylist) {
        this.ContentItemArrayList = ContentArraylist;
    }

    public static ArrayList<BannerClass> getCategoryList(JSONArray jsonArray) {

        ArrayList<BannerClass> gamesList = new ArrayList<BannerClass>();
        try {

            if (jsonArray != null && jsonArray.length()>0) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jObj = jsonArray.getJSONObject(i);

                    gamesList.add(getCategoryFromJson(jObj));

                }
            }
            return gamesList;
        } catch (Exception e) {

            return gamesList;
        }
    }

    private static BannerClass getCategoryFromJson(JSONObject jObj) {
        BannerClass contentData = new BannerClass();
        try {

            if(jObj.has("ID") && !jObj.isNull("ID"))
                contentData.setId(jObj.getInt("ID"));

            if(jObj.has("Content") && !jObj.isNull("Content"))
                contentData.setContentItemArrayList(ContentItemClass.getContentList(jObj.getJSONArray("Content")));
            return contentData;
        } catch (JSONException e) {
            return contentData;
        }
    }
}
