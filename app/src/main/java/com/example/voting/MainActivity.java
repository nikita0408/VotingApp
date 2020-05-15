//first activity having admin and user button
package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 =(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_useractivity();
            }
        });
        button2 =(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_adminactivity();
            }
        });
     }
    public void openactivity_useractivity()
    {
        Intent intent = new Intent(this,useractivity.class);
        startActivity(intent);
    }
    public void openactivity_adminactivity()
    {
        Intent intent = new Intent(this,adminactivity.class);
        startActivity(intent);
    }
}
