package com.example.afinal.fragment;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.afinal.Data;
import com.example.afinal.MySingleton;
import com.example.afinal.Opendish;
import com.example.afinal.R;
import com.example.afinal.RecyclerVIew_Adapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RequestQueue mRequestQueue;


    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore2, container, false);
        List<Data> data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerVIew_Adapter adapter = new RecyclerVIew_Adapter(data, getActivity().getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        return view;
    }



    public List<Data> fill_with_data() {
        mRequestQueue = Volley.newRequestQueue(getContext());

        List<Data> data = new ArrayList<>();
        data.add(0,new Data("Blueberry-Lavender Sauce and Ginger Snap Ice Cream Cups","Price Per Serving: + 94.2",10,45,635561,"Non-vegeterian"));
        data.add(0,new Data("Salmon with roasted vegetables","Price Per Serving: + 520.46",7,45,659135,"Non-vegeterian"));
        data.add(0,new Data("Sweet & Spicy White Cheddar Cheese Ball W/apples & Bacon","Price Per Serving: + 46.58",18,45,662428,"Non-vegeterian"));



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.spoonacular.com/recipes/random?number=30&apiKey=cd610052d5384b72a7f2acb1b8a2cbfc", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    for (int i = 0; i < 28 ; i++) {

                        JSONObject jsonObject = response.getJSONArray("recipes").getJSONObject(i);
                        String title = jsonObject.getString("title");
                        boolean vegornot=jsonObject.getBoolean("vegetarian");
                        int time=jsonObject.getInt("readyInMinutes");
                        int likes=jsonObject.getInt("aggregateLikes");
                        int imgid=jsonObject.getInt("id");
                        String pricePerServing=jsonObject.getString("pricePerServing");

                        String vegornotstr="";
                        if (vegornot==true){
                            vegornotstr="vegeterian";
                        }else{
                            vegornotstr="Non-Vegeterian";
                        }

                        data.add(i,new Data(title,"Price Per Serving: "+pricePerServing,likes,time,imgid,vegornotstr));

                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


//        mRequestQueue.add(jsonObjectRequest);
        MySingleton.getInstance(getContext()).addToRequestQue(jsonObjectRequest);

        return data;
    }





}