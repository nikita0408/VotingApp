//activity at the admin side for retrieving the questions in our database
package com.example.voting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionDisplay extends AppCompatActivity {
    String json_string,result;
    String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);
        }
    public void getd(View view) {
        new BTask().execute();
    }
    public class BTask extends AsyncTask<Void,Void,String> {
        String json_url;
        protected void onPreExecute(){
            json_url=("http://10.0.2.2/getquesdata.php");
        }
        protected String doInBackground(Void...params){
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
            return null;
        }
        protected void onPostExecute(String result){
            json_string=result;
        }
    }
    public void data(View view)
    {
        if(json_string==null)
        {
            Toast.makeText(getApplicationContext(),"Get JSON first",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this,Listdisplay2.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);
        }
    }
}
