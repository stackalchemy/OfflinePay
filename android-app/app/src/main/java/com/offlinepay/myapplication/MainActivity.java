
package com.offlinepay.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Find buttons from XML
        Button btnSend = findViewById(R.id.btnSend);
        Button btnReceive = findViewById(R.id.btnReceive);

        // Handle Send Money click
        btnSend.setOnClickListener(v -> {
            Toast.makeText(
                    MainActivity.this,
                    "Send Money clicked!",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Handle Receive Money click
        btnReceive.setOnClickListener(v -> {
            Toast.makeText(
                    MainActivity.this,
                    "Receive Money clicked!",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}
