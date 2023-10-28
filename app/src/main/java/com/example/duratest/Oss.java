package com.example.duratest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class Oss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oss);


        AlertDialog.Builder builder = new AlertDialog.Builder(Oss.this);

        // Set the message show for the Alert time
        builder.setMessage(" يرجى منكم مراجعة مركز خمات الجمهور لتحديث بياناتكم ");

        // Set Alert Title
        builder.setTitle("تنبيه  !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

            Intent intent = new Intent(getApplicationContext(), searchby.class);
        //    intent.putExtra("type", 2); //Optional parameters

            Oss.this.startActivity(intent);

            // When the user click yes button then app will close
          //  finish();



        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.

            Intent intent = new Intent(getApplicationContext(), searchby.class);
          //  intent.putExtra("type", 2); //Optional parameters

            Oss.this.startActivity(intent);

           // dialog.cancel();


        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();

    }
}