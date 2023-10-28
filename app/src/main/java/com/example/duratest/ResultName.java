package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultName extends AppCompatActivity implements NameAdapter.OnItemClicked {

    private TextView responseTV;

    private TextView numm , idtvresponse;
    private ProgressBar loadingPB;

    public LinearLayoutManager layoutManager;
    public LinearLayoutManager manager;

    private RecyclerViewAdapter adapter2;
    private RecyclerView recyclerView;
    private RecyclerView courseRV;

    List<DataModal> userList = null;

    String fname ;
    String sname;
    String thname ;
    private Context mContext;
    private ArrayList<DataModal> DataModelArrayList;

    String foname;

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

         recyclerView = (RecyclerView)findViewById(R.id.recycler);

        Intent intent = getIntent();
         fname = intent.getStringExtra("fname");
         sname = intent.getStringExtra("sname");
         thname = intent.getStringExtra("thname");

         foname = intent.getStringExtra("foname");

        DataModelArrayList = new ArrayList<>();

          postData(fname, sname, thname, foname);
    }

    private void postData(String fname, String sname, String thname, String foname) {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gis.duracity.ps/Mobile/api/Customer/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(fname, sname, foname, thname);

        // calling a method to create a post and passing our modal class.
        Call<List<DataModal>> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<List<DataModal>>() {



            @Override
            public void onResponse(Call<List<DataModal>> call, Response<List<DataModal>> response) {
                // this method is called when we get response from our api.
            //    Toast.makeText(ResultName.this, "Data added to API", Toast.LENGTH_SHORT).show();



                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                //  jobEdt.setText("");
                //  nameEdt.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                userList  = response.body();

                userList.size();

                  if (userList.size() == 0 ){

                      numm.setVisibility(View.GONE);
                      idtvresponse.setVisibility(View.GONE);
                      Toast.makeText(ResultName.this, "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();



                  }else {



                for (int o = 0 ; o < userList.size() ;  o++){

                //   Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getSysteM_NO()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

                 /*   Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getF_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getS_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

                    Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getM_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getL_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
*/


                    DataModelArrayList.add(new DataModal(userList.get(o).getF_name_a().toString(), userList.get(o).getS_name_a().toString(), userList.get(o).getM_name_a().toString(), userList.get(o).getL_name_a().toString(), userList.get(o).getSysteM_NO().toString()));

                    int listSize = DataModelArrayList.size();

                    for (int i = 0; i<listSize; i++){
                        Log.i("f name: ", String.valueOf(userList.get(i).getF_name_a().toString()));
                        Log.i("s name: ", String.valueOf(userList.get(i).getM_name_a().toString()));
                        Log.i("m name: ", String.valueOf(userList.get(i).getS_name_a().toString()));
                        Log.i("l name: ", String.valueOf(userList.get(i).getL_name_a().toString()));

                    }

                    Log.i("autolog", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");

                    layoutManager = new LinearLayoutManager(ResultName.this);
                    Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
                    recyclerView.setLayoutManager(layoutManager);
                    Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

                    NameAdapter recyclerViewAdapter =new NameAdapter(DataModelArrayList, ResultName.this);
                    Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
                    recyclerView.setAdapter(recyclerViewAdapter);
                    Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");

                    recyclerViewAdapter.setOnClick(ResultName.this);

                  // buildRecyclerView();
                }

                  }

                // on below line we are getting our data from modal class and adding it to our string.
            //  String responseString = "Response Code : " + response.code() + "\nName : " + userList.get(0).getF_name_a() + "\n";

            /*
                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getF_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getS_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getM_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getL_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

           */




                 //  buildRecyclerView();

                // below line we are setting our
                // string to our text view.
              //  responseTV.setText("result");
            }

            @Override
            public void onFailure(Call<List<DataModal>>  call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
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

    private void buildRecyclerView() {

        Toast.makeText(ResultName.this, "bulid recycler " + "bulid recycler" , Toast.LENGTH_SHORT).show();


        // initializing our adapter class.
        adapter2 = new RecyclerViewAdapter(ResultName.this, DataModelArrayList);

        // adding layout manager
        // to our recycler view.
        manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter2);

    }
}