package com.example.afinal;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView dishname,description,likes,vegornot,time;
    Button savebutton;
    ImageView dishimage,vegornotimg;
    FloatingActionButton b1;

    View_Holder(View itemView) {
        super(itemView);
        dishname = itemView.findViewById(R.id.dishname);
        dishname.setSelected(true);
        description = itemView.findViewById(R.id.description);
        likes = itemView.findViewById(R.id.likes);
        vegornot = itemView.findViewById(R.id.vegornot);
        vegornotimg = itemView.findViewById(R.id.vegornotimg);


        time = itemView.findViewById(R.id.time);
        dishimage = itemView.findViewById(R.id.dishimage);
        b1=itemView.findViewById(R.id.savebutton);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                v.getContext().startActivity();
//                Intent i = new Intent(itemView.getContext(),Opendish.class);
//                itemView.getContext().startActivity(i);
//            }
//        });


    }


    @Override
    public void onClick(View v) {

        Intent I1=new Intent(v.getContext(), Opendish.class);
        v.getContext().startActivity(I1);
    }
}