//admin activity having toolbar
package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class Main4Activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setUpToolBar();
        navigationView=(NavigationView)findViewById(R.id.navigationmenu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_1:
                        Toast.makeText(Main4Activity.this, " Click to view questions ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Main4Activity.this,QuestionDisplay.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_2:
                        Toast.makeText(Main4Activity.this, "Give questions ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Main4Activity.this,QuestionActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_3:
                        Intent in= new Intent(Main4Activity.this,ViewUser.class);
                        startActivity(in);
                        break;
                    case R.id.nav_4:
                        Toast.makeText(Main4Activity.this, " Logging Out ", Toast.LENGTH_SHORT).show();
                        Intent in1= new Intent(Main4Activity.this,MainActivity.class);
                        startActivity(in1);
                        break;
                }
                return true;
            }
        });
    }
    private void setUpToolBar()
    {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
