package com.example.confined.presentation.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bored implements Serializable {

    @SerializedName("activity")
    private String activity;
    @SerializedName("accessibility")
    private Float accessibility;
    @SerializedName("type")
    private String type;
    @SerializedName("participants")
    private Integer participants;
    @SerializedName("price")
    private Float price;
    @SerializedName("link")
    private String link;
    @SerializedName("key")
    private Integer key;


    public Bored(String activity, Float accessibility, String type, Integer participants,
                 Float price, String link, Integer key) {
        this.activity = activity;
        this.accessibility = accessibility;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.key = key;
    }


    public String getActivity() {
        return activity;
    }


    public Float getAccessibility() {
        return accessibility;
    }


    public String getType() {
        return type;
    }


    public Integer getParticipants() {
        return participants;
    }


    public Float getPrice() {
        return price;
    }


    public String getLink() {
        return link;
    }


    public Integer getKey() {
        return key;
    }
}
