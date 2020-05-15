//user activity page to enter the username for taking the test
package com.example.voting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Main6Activity extends AppCompatActivity {
    EditText Usernameet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Usernameet = (EditText) findViewById(R.id.en);
    }
    public void Onlog(View view)
    {
        String username= Usernameet.getText().toString();
        String type="login";
        B b = new B(this);
        b.execute(type,username);
    }

}









