//new user creation - registeration activity
package com.example.voting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    EditText name,password,email;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=(EditText)findViewById(R.id.ename);
        password=(EditText)findViewById(R.id.epassword);
        email=(EditText)findViewById(R.id.eemail);
    }
    public void Onregister(View view){
        String str_name= name.getText().toString();
        String str_password= password.getText().toString();
        String str_email= email.getText().toString();
        String type="register";
        BackgroundWorker2 backgroundworker2 = new BackgroundWorker2(this);
        backgroundworker2.execute(type,str_name,str_password,str_email);
    }
 }
