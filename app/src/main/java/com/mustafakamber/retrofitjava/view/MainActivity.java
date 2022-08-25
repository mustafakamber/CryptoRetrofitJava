package com.mustafakamber.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.AndroidException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mustafakamber.retrofitjava.R;
import com.mustafakamber.retrofitjava.adapter.RecyclerViewAdapter;
import com.mustafakamber.retrofitjava.model.CryptoModel;
import com.mustafakamber.retrofitjava.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

         recyclerView = findViewById(R.id.recyclerView);

        //Refrofit & JSON

        //JSON
        Gson gson = new GsonBuilder().setLenient().create();
        //Retrofit objesi olusturma
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        gettingData();

    }


    //Veriyi internetten cekme
    private void gettingData(){
        //Servis olusturuldu
        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

       //Veriyi cekme 1(RxJava)
        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(cryptoAPI.getData()
                        .subscribeOn(Schedulers.io())//hangi thread de gözlemleme islemi yapilacagi
                        .observeOn(AndroidSchedulers.mainThread())//alinan sonuclari main thread de goster
                        .subscribe(this::handleResponse));//ortaya cikan sonucu nerede ele alacagiz





        /*

        Veriyi cekme 2

        Call<List<CryptoModel>> call = cryptoAPI.getData();//Veriyi  interfaceden cekme

        call.enqueue(new Callback<List<CryptoModel>>() {//enqueue ---> asekron bir sekilde islemi yap
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    //RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
                //Toast mesaji yazdir
            }
        });







*/


    }
    private void handleResponse(List<CryptoModel> cryptoModelsList){
        cryptoModels = new ArrayList<>(cryptoModelsList);

        //RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

       @Override
       public void onDestroy(){
        super.onDestroy();

        compositeDisposable.clear();
       }
}