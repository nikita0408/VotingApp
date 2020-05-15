//admin activity - to add the question in the database
package com.example.voting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    EditText ques,ans1,ans2,ans3;
    String str_ques,str_ans1,str_ans2,str_ans3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ques=(EditText)findViewById(R.id.eques);
        ans1=(EditText)findViewById(R.id.eans1);
        ans2=(EditText)findViewById(R.id.eans2);
        ans3=(EditText)findViewById(R.id.eans3);

    }
    public void add(View view){
        String str_ques= ques.getText().toString();
        String str_ans1= ans1.getText().toString();
        String str_ans2= ans2.getText().toString();
        String str_ans3= ans3.getText().toString();
        String type="add";
        BackgroundWorker3 backgroundworker3 = new BackgroundWorker3(this);
        backgroundworker3.execute(type,str_ques,str_ans1,str_ans2,str_ans3);

    }
}
