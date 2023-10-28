package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class resultID extends AppCompatActivity {

    private RecyclerView courseRV;

    private TableLayout table1;
    private TableLayout table2;

    private TextView name;
    private TextView mob;

    private TextView ParNum , testid;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;

    // below line is the variable for url from
    // where we will be querying our data.
    String url ;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        table1 = findViewById(R.id.table1);
        table2 =findViewById(R.id.table2);


        Intent intent = getIntent();
        url = intent.getStringExtra("URL");

       // Toast.makeText(getApplicationContext(), url  ,Toast.LENGTH_SHORT).show();
        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);

        name = findViewById(R.id.test1);
        mob = findViewById(R.id.test2);
        ParNum =  findViewById(R.id.test3);
        testid = findViewById(R.id.testid);

        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();
        getData();

        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);

                if (response.length() == 0 ){

                    table1.setVisibility(View.GONE);
                    table2.setVisibility(View.GONE);
                    Toast.makeText(resultID.this, "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

                }
                else{
                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);

                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.
                        String courseName = responseObj.getString("arabiC_NAME");
                        String courseTracks = responseObj.getString("mobile");
                        String courseMode = responseObj.getString("balance");
                        String courseImageURL = responseObj.getString("systeM_NO");

                        String idcard_no = responseObj.getString("idcard_no");

                        String systeM_NO = responseObj.getString("systeM_NO");
                        String arabiC_NAME = responseObj.getString("arabiC_NAME");
                        String mobile = responseObj.getString("mobile");
                        String balance = responseObj.getString("balance");
                        String servS_TYPE = responseObj.getString("servS_TYPE");
                        String agreemenT_ID = responseObj.getString("agreemenT_ID");
                        String currencY_ID = responseObj.getString("currencY_ID");
                        String quarter_id = responseObj.getString("quarter_id");
                        String block_id = responseObj.getString("block_id");
                        String parcel_id = responseObj.getString("parcel_id");
                        String id = "";


                        name.setText(arabiC_NAME);
                        mob.setText(mobile);
                        ParNum.setText(systeM_NO);
                        testid.setText(idcard_no);


                        courseModalArrayList.add(new CourseModal(systeM_NO, arabiC_NAME, mobile, balance, servS_TYPE, agreemenT_ID, currencY_ID, quarter_id, block_id, parcel_id, id));
                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(resultID.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModalArrayList, resultID.this);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }
}
