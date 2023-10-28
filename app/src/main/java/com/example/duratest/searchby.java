package com.example.duratest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchby extends AppCompatActivity {

    TextView tv ;
    EditText EtText;
    Button Search;
    Intent intentt;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchby);

        tv = (TextView) findViewById(R.id.textView) ;
        EtText = (EditText) findViewById(R.id.editText) ;
        Search = (Button) findViewById(R.id.search) ;

        Intent intent = getIntent();
        value = intent.getIntExtra("type", 1);

       // Toast.makeText(getApplicationContext(), value+""  ,Toast.LENGTH_SHORT).show();



        tv = (TextView) findViewById(R.id.textView) ;
        if (value == 1 ) {
            tv.setText(" ادخل رقم الموبايل ");

        }

        if (value == 2 ) {
            tv.setText(" ادخل رقم الهوية ");

        }

        if (value == 3 ) {
            tv.setText(" ادخل الاسم ");

        }

        Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (EtText.getText().toString().equals("")){

                    if (value == 1) {
                        Toast.makeText(getApplicationContext(), "ادخل رقم الموبايل "  ,Toast.LENGTH_SHORT).show();

                    }

                    if (value == 2) {
                        Toast.makeText(getApplicationContext(), "ادخل رقم الهوية  "  ,Toast.LENGTH_SHORT).show();

                    }




                }else{


                    String Link = "http://gis.duracity.ps/Mobile/api/Customer/MobileCustomerName/";

                    String Etvalue = EtText.getText().toString();

                    String MobValue = Link + "" + Etvalue;




                    if (value == 1) {
                        intentt = new Intent(getApplicationContext(), result.class);
                        intentt.putExtra("URL", MobValue); //Optional parameters
                        //  Toast.makeText(getApplicationContext(), MobValue+""  ,Toast.LENGTH_SHORT).show();

                    }

                    if (value == 2) {
                        intentt = new Intent(getApplicationContext(), resultID.class);
                        intentt.putExtra("URL", "http://gis.duracity.ps/Mobile/api/Customer/DocumentNum/" + Etvalue); //Optional parameters
                    }

                    if (value == 3) {
                        //    intent.putExtra("URL", ""+Etvalue); //Optional parameters

                    }


                    searchby.this.startActivity(intentt);

                }

            }
        });



    }
}