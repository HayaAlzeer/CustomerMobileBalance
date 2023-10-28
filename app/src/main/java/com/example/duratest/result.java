package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class result extends AppCompatActivity implements NameAdapter.OnItemClicked {


    private RecyclerView courseRV;


    public ArrayList<HashMap<String, String>> collection;


    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;
    private ArrayList<DataModal> DataModelArrayList;
    // below line is the variable for url from
    // where we will be querying our data.
    String url;

    public LinearLayoutManager layoutManager11;


    private RecyclerView recyclerView11;


    private ProgressBar progressBar;
    ArrayList<String> MainListItem;
    ArrayList<String> duplicates;
    Set<String> set;

    private TextView numm , idtvresponse;


    private TextView responseTV;

    private ProgressBar loadingPB;


    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_name);


        mContext = this;

        loadingPB = findViewById(R.id.idLoadingPB);

        courseRV = findViewById(R.id.recycler);
        responseTV = findViewById(R.id.idTVResponse);

        numm = findViewById(R.id.numm);
        idtvresponse = findViewById(R.id.idTVResponse);


        recyclerView11 = (RecyclerView) findViewById(R.id.recycler);

        DataModelArrayList = new ArrayList<>();


        MainListItem = new ArrayList<String>();
        set = new HashSet<>();
        duplicates = new ArrayList<String>();

        Intent intent = getIntent();
        url = intent.getStringExtra("URL");

        //  Toast.makeText(getApplicationContext(), url  ,Toast.LENGTH_SHORT).show();

        Log.d("ADebugTag", "Value: " + url);

        // initializing our variables.
      /*  courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);

        name = findViewById(R.id.test1);
        mob = findViewById(R.id.test2);
        ParNum =  findViewById(R.id.test3);
        idCard = findViewById(R.id.testid);
*/
        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();


      //  getDataNum();
        getDataMobile();


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                //  dialog.dismiss();
            }
        });


    }


    @Override
    public void onBackPressed() {
        // code here to show dialog
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //Optional parameters
        result.this.startActivity(intent);


        // optional depending on your needs
    }


    private void getDataNum() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        Log.d("ADebugTag", "Value: " + url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {

                Log.d("ADebugTag", "Value: " + "" + response.length());

                loadingPB.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {

                    Log.d("ADebugTag", "Value: " + "" + response.length());

                    try {

                        JSONObject responseObj = response.getJSONObject(i);

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
                        String idcard_no = responseObj.getString("idcard_no");
                        String f_name_a = responseObj.getString("f_name_a");
                        String s_name_a = responseObj.getString("s_name_a");
                        String m_name_a = responseObj.getString("m_name_a");
                        String l_name_a = responseObj.getString("l_name_a");


                        if (!MainListItem.contains(systeM_NO)) {

                            MainListItem.add(systeM_NO);
                        }


                        String id = "";


                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);

                        // courseModalArrayList.add(new CourseModal( systeM_NO,  arabiC_NAME,  mobile,  balance,  servS_TYPE,  agreemenT_ID,  currencY_ID,  quarter_id,  block_id,  parcel_id,  id,  idcard_no,  f_name_a, s_name_a,  m_name_a,  l_name_a));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                // Toast.makeText(getApplicationContext(), MainListItem.size()+""  ,Toast.LENGTH_SHORT).show();

                for (int j = 0; j < MainListItem.size(); j++) {

                    //     Toast.makeText(getApplicationContext(), MainListItem.get(j)+"" ,Toast.LENGTH_SHORT).show();
                  //  getDataSystemNo(MainListItem.get(j));


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(result.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();

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

    private void getDataMobile() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {

                Log.d("ADebugTag", "Value: " + "" + response.length());

                loadingPB.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);

                if (response.length() == 0 ){

                    numm.setVisibility(View.GONE);
                    idtvresponse.setVisibility(View.GONE);

                    Toast.makeText(result.this, "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();


                }
                else{

                for (int i = 0; i < response.length(); i++) {

                    //    Toast.makeText(getApplicationContext(), " haya length"+response.length()  ,Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(), " haya "  ,Toast.LENGTH_SHORT).show();
                    Log.d("ADebugTag", "Value: " + "" + response.length());

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


                        DataModelArrayList.add(new DataModal(f_name_a, s_name_a, m_name_a, l_name_a, systeM_NO));

                        // Toast.makeText(getApplicationContext(), " system no  "+ systemNo  ,Toast.LENGTH_SHORT).show();

                        layoutManager11 = new LinearLayoutManager(result.this);
                        Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
                        recyclerView11.setLayoutManager(layoutManager11);
                        Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

                        NameAdapter recyclerViewAdapter = new NameAdapter(DataModelArrayList, result.this);

                        // Toast.makeText(getApplicationContext(), " DataModelArrayList size "+ DataModelArrayList .size()   ,Toast.LENGTH_SHORT).show();

                        Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
                        recyclerView11.setAdapter(recyclerViewAdapter);
                        Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");

                        recyclerViewAdapter.setOnClick(result.this);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(result.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onItemClick(int position) {
        DataModal aim = DataModelArrayList.get(position);
        Intent in = new Intent(this, getsystemNo.class);

        String trackName = aim.getF_name_a();
        String collectionName = aim.getS_name_a();
        String artistName = aim.getL_name_a();
        String genreName = aim.getM_name_a();
        String albumArt = aim.getSysteM_NO();

        in.putExtra("track", trackName);
        in.putExtra("collection", collectionName);
        in.putExtra("artist", artistName);
        in.putExtra("genre", genreName);
        in.putExtra("albumart", albumArt);
        startActivity(in);
    }
}
