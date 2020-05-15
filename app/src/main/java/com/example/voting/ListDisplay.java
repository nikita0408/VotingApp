//class to display the list of users of our app by retrieving from the database
package com.example.voting;

import android.os.Bundle;
import android.widget.ListView;
import com.example.voting.Model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

public class ListDisplay extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Userdisplaysupplier userdisplaysupplier;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);
        listView=(ListView)findViewById(R.id.lview);
        userdisplaysupplier=new Userdisplaysupplier(this,R.layout.rowlayout6);
        listView.setAdapter(userdisplaysupplier);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            String name,pass,email;
            while(count<jsonArray.length())
            {
                JSONObject JO= jsonArray.getJSONObject(count);
                name=JO.getString("Username");
                pass=JO.getString("password");
                email=JO.getString("email");
                User user=new User(name,pass,email);
                count++;
                userdisplaysupplier.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
