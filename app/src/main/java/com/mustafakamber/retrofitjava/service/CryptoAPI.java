package com.mustafakamber.retrofitjava.service;

import com.mustafakamber.retrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //GET, POST, UPDATE, DELETE

    //URL BASE   www.website.com
    // GET    price?key?=xxx

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")//Veriyi alma istegi yolla
    Observable<List<CryptoModel>> getData();

    //Call<List<CryptoModel>> getData();//Bana bir liste icerisinde butun cripto modelleri gelecek


}
