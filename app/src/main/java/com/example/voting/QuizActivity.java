//activity at the user side showing all the questions to the user and taking input from user by selection of the radio button
package com.example.voting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.voting.Model.Question;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private TextView quizQuestion;
    private RadioGroup radioGroup;
    private RadioButton optionOne;
    private RadioButton optionTwo;
    private RadioButton optionThree;
    private int currentQuizQuestion;
    private int quizCount;
    private Question firstQuestion;
    private List<Question> parsedObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        quizQuestion = (TextView)findViewById(R.id.quiz_question);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        optionOne = (RadioButton)findViewById(R.id.radio0);
        optionTwo = (RadioButton)findViewById(R.id.radio1);
        optionThree = (RadioButton)findViewById(R.id.radio2);
        Button nextButton = (Button)findViewById(R.id.nextquiz);
        Button exit = (Button)findViewById(R.id.exit);
        AsyncJsonObject asyncObject = new AsyncJsonObject();
        asyncObject.execute("");
        nextButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            int radioSelected = radioGroup.getCheckedRadioButtonId();
            String userSelection = getSelectedAnswer(radioSelected);
            firstQuestion = parsedObject.get(currentQuizQuestion);
            quizQuestion.setText(firstQuestion.getQuestion());
            uncheckedRadioButton();
            String question=quizQuestion.getText().toString();
            String type="res";
            bg bg = new bg(QuizActivity.this);
            bg.execute(type,question,userSelection);
             currentQuizQuestion++;
                    if(currentQuizQuestion >= quizCount){
                        Toast.makeText(QuizActivity.this, "End of the Questions", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else{
                        firstQuestion = parsedObject.get(currentQuizQuestion);
                        quizQuestion.setText(firstQuestion.getQuestion());
                        uncheckedRadioButton();
                        optionOne.setText(firstQuestion.getAns1());
                        optionTwo.setText(firstQuestion.getAns2());
                        optionThree.setText(firstQuestion.getAns3());
                    }
               }
    });
       exit.setOnClickListener(new View.OnClickListener() {
           Context context;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this,Main5Activity.class);
                startActivity(intent);
            }
        });
    }
    private class AsyncJsonObject extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;
        String json_url;
        protected void onPreExecute() {
           super.onPreExecute();
            json_url=("http://10.0.2.2/i.php");
            progressDialog = ProgressDialog.show(QuizActivity.this, "Loading","Wait....", true);
        }
        protected String doInBackground(String...params){
            String JSON_STRING = "",result=" ";
            try{
                URL url=new URL (json_url);
                HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream=httpurlconnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpurlconnection.disconnect();
                result=stringBuilder.toString().trim();
                return result;
               }
            catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            System.out.println("Resulted Value: " + result);
            parsedObject = returnParsedJsonObject(result);
            if(parsedObject == null){
                return;
            }
            quizCount = parsedObject.size();
            firstQuestion = parsedObject.get(0);
            quizQuestion.setText(firstQuestion.getQuestion());
            optionOne.setText(firstQuestion.getAns1());
            optionTwo.setText(firstQuestion.getAns2());
            optionThree.setText(firstQuestion.getAns3());
        }
    }
    private List<Question> returnParsedJsonObject(String result){
        List<Question> jsonObject = new ArrayList<Question>();
        JSONObject resultObject = null;
        JSONArray jsonArray = null;
        Question newItemObject = null;
        try {
            resultObject = new JSONObject(result);
            System.out.println("Testing the water " + resultObject.toString());
            jsonArray = resultObject.optJSONArray("questiontable");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonChildNode = null;
            try {
                jsonChildNode = jsonArray.getJSONObject(i);
                int id = jsonChildNode.getInt("id");
                String question = jsonChildNode.getString("question");
                String ans1 = jsonChildNode.getString("ans1");
                String ans2 = jsonChildNode.getString("ans2");
                String ans3 = jsonChildNode.getString("ans3");
                newItemObject = new Question(id, question, ans1,ans2,ans3);
                jsonObject.add(newItemObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    private String getSelectedAnswer(int radioSelected)
    {
        String answerSelected = "";
        if(radioSelected == R.id.radio0){
            answerSelected = "a";
        }
        if(radioSelected == R.id.radio1){
            answerSelected = "b";
        }
        if(radioSelected == R.id.radio2){
            answerSelected = "c";
        }
        return answerSelected;
    }
    private void uncheckedRadioButton()
    {
        optionOne.setChecked(false);
        optionTwo.setChecked(false);
        optionThree.setChecked(false);
    }
}










