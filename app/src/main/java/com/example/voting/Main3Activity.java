//user login activity
package com.example.voting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {
    EditText Usernameet,Passwordet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Usernameet = (EditText) findViewById(R.id.editTextPersonname);
        Passwordet = (EditText) findViewById(R.id.editTextPassword);
    }
    public void Onlogin(View view)
    {
        String username= Usernameet.getText().toString();
        String password= Passwordet.getText().toString();
        String type="login";
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.execute(type,username,password);
    }
}

