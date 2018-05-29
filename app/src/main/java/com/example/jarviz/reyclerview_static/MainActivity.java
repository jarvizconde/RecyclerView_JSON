package com.example.jarviz.reyclerview_static;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //this is the JSON Data URL
    private static final String PRODUCT_URL = "http://192.168.1.10:8081/MyApi/api.php";
    //the recyclerview
    RecyclerView recyclerView;


    ProductAdapter  adapter;
    //a list to store all the products
    List <Product> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById( R.id.recyckerViewId);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();


      loadProducts();





    }
    private void loadProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray products = new JSONArray(response);

                            for (int i = 0; i < products.length(); i++) {

                                //getting product object from json array
                                JSONObject productssJASON = products.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(
                                        productssJASON.getInt("id"),
                                        productssJASON.getString("title"),
                                        productssJASON.getString("shortdesc"),
                                        productssJASON.getDouble("rating"),
                                        productssJASON.getDouble("price"),
                                        productssJASON.getString("image")
                                ));
                            }


                            //creating recyclerview adapter
                            ProductAdapter adapter = new ProductAdapter(MainActivity.this, productList);

                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                                e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
