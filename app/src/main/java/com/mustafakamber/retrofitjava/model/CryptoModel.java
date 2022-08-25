package com.mustafakamber.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    //SerializedName ====> Data(Jsondaki veri) ile "xxxxx" iceriginin ayni olmasi gerekiyor


    @SerializedName("currency")
    public String currency;

    @SerializedName("price")
    public String price;


}
