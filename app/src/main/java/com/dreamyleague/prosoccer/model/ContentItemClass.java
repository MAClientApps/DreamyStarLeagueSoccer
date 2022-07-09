package com.dreamyleague.prosoccer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContentItemClass {
    public static String tag = "ContentItem";

    String Id,Content,Thumbnail,Title,Thumbnail_Large;

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        ContentItemClass.tag = tag;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail_Large() {
        return Thumbnail_Large;
    }

    public void setThumbnail_Large(String thumbnail_Large) {
        Thumbnail_Large = thumbnail_Large;
    }

    public static ArrayList<ContentItemClass> getContentList(JSONArray jsonArray) {

        ArrayList<ContentItemClass> gamesList = new ArrayList<ContentItemClass>();
        try {

            if (jsonArray != null && jsonArray.length()>0) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jObj = jsonArray.getJSONObject(i);

                    gamesList.add(getContentFromJson(jObj));

                }
            }
            return gamesList;
        } catch (Exception e) {

            return gamesList;
        }
    }

    private static ContentItemClass getContentFromJson(JSONObject jObj) {
        ContentItemClass contentitemClassDataTD = new ContentItemClass();
        try {

            if (jObj.has("ID") && !jObj.isNull("Id"))
                contentitemClassDataTD.setId(jObj.getString("Id"));

            if(jObj.has("Content") && !jObj.isNull("Content"))
                contentitemClassDataTD.setContent(jObj.getString("Content"));

            if(jObj.has("Thumbnail") && !jObj.isNull("Thumbnail"))
                contentitemClassDataTD.setThumbnail(jObj.getString("Thumbnail"));

            if (jObj.has("Title") && !jObj.isNull("Title"))
                contentitemClassDataTD.setTitle(jObj.getString("Title"));


            return contentitemClassDataTD;

        } catch (JSONException e) {

            return contentitemClassDataTD;
        }
    }
}
