//background worker class for registeration of a new user
package com.example.voting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

public class BackgroundWorker2 extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker2()
    {}
    BackgroundWorker2(Context ctx)
    {
        context = ctx;
    }
    @Override
    protected String doInBackground(String...params) {
        String type = params[0];
        String createaccount_url= "http://10.0.2.2/register.php";
        if(type.equals("register")){
            try{
                String username = params[1];
                String password= params[2];
                String email= params[3];
                URL url=new URL (createaccount_url);
                HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.setDoOutput(true);
                httpurlconnection.setDoInput(true);
                OutputStream outputstream = httpurlconnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputstream.close();
                InputStream inputStream = httpurlconnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
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
        alertDialog.setTitle("Login Status");
    }
    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
        if(result.equals("New User insert successful.."))
        {
            Intent intent = new Intent();
            intent.setClass(context.getApplicationContext(), Main3Activity.class);
            context.startActivity(intent);
        }
        else
        {
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    alertDialog.dismiss();
                    timer.cancel();
                }
            }, 1000);
        }
    }
    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
