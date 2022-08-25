package com.mustafakamber.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafakamber.retrofitjava.R;
import com.mustafakamber.retrofitjava.model.CryptoModel;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<CryptoModel> cryptoList;

    private String[] colors = {"#ff55ff","#55ff55","#321cba","#55ff55","#575f2a","#eac784","#d7503c","#6f7fca","#ff9e18"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //row_layout ile RecyclerView'in birbirine baglama islemleri

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
         //Gorunumleri baglama islemleri

        holder.bind(cryptoList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        //Kac tane row olusturulacak? (Kac tane veri geliyorsa)
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(CryptoModel cryptoModel,String[] colors,Integer position){


            itemView.setBackgroundColor(Color.parseColor(colors[position % 9]));//CryptoModellerin arkaplan rengini degistirme
            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById(R.id.text_price);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price);
        }
    }
}
