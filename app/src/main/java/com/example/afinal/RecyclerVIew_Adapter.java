package com.example.afinal;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.afinal.DATA.MyDbHandler;
import com.example.afinal.PARAMS.Parmas;

import java.util.Collections;
import java.util.List;

public class RecyclerVIew_Adapter extends RecyclerView.Adapter<View_Holder> {

    List<Data> list = Collections.emptyList();
    Context context;

    public RecyclerVIew_Adapter(List<Data> data, Application application) {
        this.list = data;
        this.context = application;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.dishname.setText(list.get(position).dishname);
        holder.description.setText(list.get(position).description);
        holder.likes.setText(String.valueOf(list.get(position).likes));
        holder.time.setText(String.valueOf(list.get(position).time));
        holder.vegornot.setText(String.valueOf(list.get(position).vegornot));
        if (String.valueOf(list.get(position).vegornot)=="Non-vegeterian") {
            holder.vegornotimg.setImageResource(R.drawable.logononveg);
        }else{
            holder.vegornotimg.setImageResource(R.drawable.veg);
        }



//        create db

        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Saved Dish", Toast.LENGTH_SHORT).show();

                try {
                    DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext() );
                    dbHelper.insertData(String.valueOf(list.get(position).id));
                    Toast.makeText(context, "Saved Dish", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    throw e;
                }


            }
        });




        String imgstr=list.get(position).imgid;
        Glide.with(context).load(""+imgstr).into(holder.dishimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opendish=new Intent(context,Opendish.class);
                opendish.putExtra("dishid", String.valueOf(list.get(position).id));
                opendish.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(opendish);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}