//user activity having existing user and new user button
package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class useractivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);
        button1 =(Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_activity_main2();
            }
        });
        button2 =(Button) findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_activity_main3();
            }
        });
    }
    public void openactivity_activity_main2()
    {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public void openactivity_activity_main3()
    {
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}



