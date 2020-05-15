//background worker class to add the selected ans of the quiz in the database
package com.example.voting;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class bg extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alertDialog;
        bg()
        {}
        bg(Context ctx)
        {
            context = ctx;
        }
        @Override
        protected String doInBackground(String...params) {
            String type = params[0];
            String result_url= "http://10.0.2.2/result.php";
            if(type.equals("res")){
                try{
                    String question = params[1];
                    String userselection= params[2];
                    URL url=new URL (result_url);
                    HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
                    httpurlconnection.setRequestMethod("POST");
                    httpurlconnection.setDoOutput(true);
                    httpurlconnection.setDoInput(true);
                    OutputStream outputstream = httpurlconnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                    String post_data= URLEncoder.encode("question","UTF-8")+"="+ URLEncoder.encode(question,"UTF-8")+"&"
                            +URLEncoder.encode("userselection","UTF-8")+"="+ URLEncoder.encode(userselection,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputstream.close();
                    InputStream inputStream = httpurlconnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while((line=bufferedReader.readLine())!=null){
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconnection.disconnect();
                    return result;
                }
                catch(MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPreExecute()
        {
            alertDialog =new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Result Status");
        }
        @Override
        protected void onPostExecute(String result)
        {
            alertDialog.setMessage(result);
            alertDialog.show();
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    alertDialog.dismiss();
                    timer.cancel();
                }
            }, 1000);
        }
        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }
    }


