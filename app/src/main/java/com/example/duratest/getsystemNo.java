package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class getsystemNo extends AppCompatActivity {

    ProgressDialog progress;

    private RecyclerView courseRV;
    HashMap<String, String> queryValues;

    public ArrayList<HashMap<String, String>> collection ;

    private TextView name;
    private TextView mob;

    private TextView ParNum;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;
    String systemNo ;
    // below line is the variable for url from
    // where we will be querying our data.
    String url ;
    private TextView idCard ;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
         systemNo = intent.getStringExtra("albumart");

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);

        name = findViewById(R.id.test1);
        mob = findViewById(R.id.test2);
        ParNum =  findViewById(R.id.test3);
        idCard = findViewById(R.id.testid);


        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();

        getData(systemNo);

      //  Toast.makeText(getApplicationContext(), systemNo  ,Toast.LENGTH_SHORT).show();
    }



    private void getData(String systemNo) {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,   "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/"+ systemNo, null , new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {

                Log.d("ADebugTag", "Value: " +""+response.length()  );

                progressBar.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {

                //    Toast.makeText(getApplicationContext(), " haya length"+response.length()  ,Toast.LENGTH_SHORT).show();
                //    Toast.makeText(getApplicationContext(), " haya "  ,Toast.LENGTH_SHORT).show();
                    Log.d("ADebugTag", "Value: " +""+response.length()  );

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
                        String idcard_no = responseObj.getString("idcard_no");
                        String f_name_a = responseObj.getString("f_name_a");
                        String s_name_a = responseObj.getString("s_name_a");
                        String m_name_a = responseObj.getString("m_name_a");
                        String l_name_a = responseObj.getString("l_name_a");


                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);
                        name.setText( arabiC_NAME);
                        mob.setText(mobile);
                        ParNum.setText(systeM_NO);
                        idCard.setText(idcard_no);
                        courseModalArrayList.add(new CourseModal( systeM_NO,  arabiC_NAME,  mobile,  balance,  servS_TYPE,  agreemenT_ID,  currencY_ID,  quarter_id,  block_id,  parcel_id,  id));
                      // courseModalArrayList.add(new CourseModal( systeM_NO,  arabiC_NAME,  mobile,  balance,  servS_TYPE,  agreemenT_ID,  currencY_ID,  quarter_id,  block_id,  parcel_id,  id,  idcard_no,  f_name_a,  s_name_a,  m_name_a,  l_name_a));

                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getsystemNo.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();

            }
        });
        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModalArrayList, getsystemNo.this);

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