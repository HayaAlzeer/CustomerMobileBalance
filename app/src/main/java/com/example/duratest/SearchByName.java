package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchByName extends AppCompatActivity {

    private TextView responseTV;

    private ArrayList<DataModal> DataModelArrayList;


    Button Search;

    EditText fname, sname, thname, foname;

    String sfname, ssname, sthname, sfoname;;

    private ProgressBar loadingPB;

    private LinearLayoutManager layoutManager;

    private RecyclerViewAdapter adapter2;

    private RecyclerView courseRV;

    List<DataModal> userList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);


        Search = (Button) findViewById(R.id.search) ;

        fname = (EditText) findViewById(R.id.fname) ;
        sname = (EditText) findViewById(R.id.sname) ;
        thname = (EditText) findViewById(R.id.thname) ;
        foname = (EditText) findViewById(R.id.foname) ;
        responseTV = findViewById(R.id.idTVResponse);

      //  loadingPB = findViewById(R.id.idLoadingPB);





        Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                // calling a method to post the data and passing our name and job.
              //  postData(fname.getText().toString(), sname.getText().toString(), thname.getText().toString(), foname.getText().toString());


                if (fname.getText().toString().equals("") && sname.getText().toString().equals("") && thname.getText().toString().equals("") && foname.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "ادخل الاسم الذي تريد البحث عنه  "  ,Toast.LENGTH_SHORT).show();

                }
                else {


                Intent intent = new Intent(getApplicationContext(), ResultName.class);


                intent.putExtra("fname", fname.getText().toString().trim());
                intent.putExtra("sname", sname.getText().toString().trim());
                intent.putExtra("thname", thname.getText().toString().trim());
                intent.putExtra("foname", foname.getText().toString().trim());

                SearchByName.this.startActivity(intent);

                }

            }
        });
    }



}