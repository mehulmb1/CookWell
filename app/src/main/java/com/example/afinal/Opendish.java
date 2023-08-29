package com.example.afinal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Opendish extends AppCompatActivity {

    TextView tw1,tw2;

    ImageView IV1,IV2;
    Button buttonneutrion,recipe;
    Dialog d1;
    String myData="665203";
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opendish);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tw1=findViewById(R.id.dishname);
        IV1=findViewById(R.id.dishimage);
        recipe=findViewById(R.id.buttonforrecipe);

         myData = getIntent().getStringExtra("dishid");



        buttonneutrion=findViewById(R.id.buttonneutrion);
        d1 = new Dialog(this);
        buttonneutrion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d1.setContentView(R.layout.activity_popup_neutrition);
                ArrayList<String> items=new ArrayList<>();

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.spoonacular.com/recipes/"+myData+"/nutritionWidget.json?apiKey=cd610052d5384b72a7f2acb1b8a2cbfc", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            items.add("calories"+ "  "+ response.getString("calories"));
                            items.add("carbs"+ "  "+ response.getString("carbs"));
                            items.add("fat"+ "  "+ response.getString("fat"));
                            items.add("protein"+ "  "+ response.getString("protein"));
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(Opendish.this, android.R.layout.simple_list_item_1,items) ;
                            ListView listView = d1.findViewById(R.id.listforneutrition);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error"+error, "onErrorResponse: ");
                    }
                });
                MySingleton.getInstance(d1.getContext()).addToRequestQue(jsonObjectRequest);
                d1.show();
            }
        });

        myData = getIntent().getStringExtra("dishid");
        Toast.makeText(this, myData, Toast.LENGTH_SHORT).show();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.spoonacular.com/recipes/"+myData+"/information?includeNutrition=false&apiKey=cd610052d5384b72a7f2acb1b8a2cbfc", null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {

                try {
                        String title = response.getString("title");
                        tw1.setText(title);
                        String url=response.getString("image");
                        Glide.with(Opendish.this).load(""+url).into(IV1);

                    String sourceUrl=response.getString("sourceUrl");


                    recipe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Opendish.this, sourceUrl, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(sourceUrl));
                            startActivity(intent);
                        }
                    });





                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });


        MySingleton.getInstance(this).addToRequestQue(jsonObjectRequest);




//        similar dish listview




        }
}
