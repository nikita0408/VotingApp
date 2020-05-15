//class to display the list of questions of our app by retrieving from the database
package com.example.voting;

import android.os.Bundle;
import android.widget.ListView;
import com.example.voting.Model.Question;
import com.example.voting.Model.Questiondisplay;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;
public class Listdisplay2 extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Questiondisplay questiondisplay;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdisplay2);
        listView=(ListView)findViewById(R.id.liview);
        questiondisplay=new Questiondisplay(this,R.layout.rowlayout);
        listView.setAdapter(questiondisplay);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0,id;
            String q,a1,a2,a3;
            while(count<jsonArray.length())
            {
                JSONObject JO= jsonArray.getJSONObject(count);
                id=JO.getInt("id");
                q=JO.getString("question");
                a1=JO.getString("ans1");
                a2=JO.getString("ans2");
                a3=JO.getString("ans3");
                Question ques=new Question(id,q,a1,a2,a3);
                count++;
                Questiondisplay.add(ques);
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }
 }





