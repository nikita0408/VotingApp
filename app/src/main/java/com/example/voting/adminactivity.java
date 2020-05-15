//admin activity to login
package com.example.voting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class adminactivity extends AppCompatActivity {
    EditText Usernameet,Passwordet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminactivity);
        Usernameet = (EditText) findViewById(R.id.editname);
        Passwordet = (EditText) findViewById(R.id.editPassword);
    }
    public void onLogin(View view)
    {
        String username= Usernameet.getText().toString();
        String password= Passwordet.getText().toString();
        String type="login";
        BackgroundWorker1 backgroundworker1 = new BackgroundWorker1(this);
        backgroundworker1.execute(type,username,password);
    }
}

