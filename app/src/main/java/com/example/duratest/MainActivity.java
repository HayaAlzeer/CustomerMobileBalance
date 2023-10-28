package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mobileSearch;
    Button nameSearch;



    Button IdSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mobileSearch = (Button) findViewById(R.id.mobilesearch) ;

        mobileSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), searchby.class);
                intent.putExtra("type", 1); //Optional parameters

                MainActivity.this.startActivity(intent);


            }
        });

        IdSearch = (Button) findViewById(R.id.idsearch) ;

        IdSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), searchby.class);
                intent.putExtra("type", 2); //Optional parameters

                MainActivity.this.startActivity(intent);


            }
        });


        nameSearch = (Button) findViewById(R.id.namesearch) ;

        nameSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



               Intent intent = new Intent(getApplicationContext(), SearchByName.class);
                intent.putExtra("type", 3); //Optional parameters

                MainActivity.this.startActivity(intent);




            }
        });


    }


}