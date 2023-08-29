package com.example.afinal.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.afinal.Data;
import com.example.afinal.MySingleton;
import com.example.afinal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RequestQueue mRequestQueue;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    FloatingActionButton b1;
    EditText Et1;
    List<Data> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);



        b1= view.findViewById(R.id.floatingActionButton);
        Et1 = view.findViewById(R.id.searchdishname);


            b1.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    if (Et1.getText().toString().equals("")){
//                        Toast.makeText(getContext(), "Enter Dish Name", Toast.LENGTH_SHORT).show();
                    }else{
                        String dishname= Et1.getText().toString();
                         data = fill_with_data(dishname);
                        ListView listView = view.findViewById(R.id.recyclerView);
                        customadapter customBaseAdapter=new customadapter();
                        listView.setAdapter(customBaseAdapter);
                }}
            });


        return view;
    }

    public List<Data> fill_with_data(String str) {
        mRequestQueue = Volley.newRequestQueue(getContext());
        List<Data> data = new ArrayList<>();
        data.add(new Data("pasta","pastawithdescription",10,30,665203,"vegeterian"));
        data.add(new Data("pasta","pastawithdescription",10,30,665203,"vegeterian"));
        data.add(new Data("pasta","pastawithdescription",10,30,665203,"vegeterian"));
        data.add(new Data("pasta","pastawithdescription",10,30,665203,"vegeterian"));


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.spoonacular.com/recipes/complexSearch?query="+str+"&apiKey=cd610052d5384b72a7f2acb1b8a2cbfc", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(getContext(), "on response", Toast.LENGTH_SHORT).show();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONArray("results").getJSONObject(i);

                        String title = jsonObject.getString("title");
                        int imgid=jsonObject.getInt("id");
//                        temporary
                        data.add(new Data(title,imgid));


                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "in exception", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "onerror", Toast.LENGTH_SHORT).show();
            }
        });



//        mRequestQueue.add(jsonObjectRequest);
        MySingleton.getInstance(getContext()).addToRequestQue(jsonObjectRequest);

        return data;
    }
    class customadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.row_layoutforsearch,null);

            TextView tw1=convertView.findViewById(R.id.dishname);
            ImageView Iv1 = convertView.findViewById(R.id.dishimage);

            tw1.setText(data.get(position).dishname);
            String imgstr=data.get(position).imgid;
            Glide.with(getActivity()).load(""+imgstr).into(Iv1);
            return convertView;
        }


    }




}