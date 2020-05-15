//adapter class showing the user in the database
package com.example.voting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.voting.Model.User;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Userdisplaysupplier extends ArrayAdapter{
    List list = new ArrayList();
    public Userdisplaysupplier(@NonNull ListDisplay context, int resource) {
        super(context, resource);
    }
    public void add(User object)
    {
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    public View getView(int position,View convertView, ViewGroup parent)
    {
        View row;
        row=convertView;
        UserHolder userHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.rowlayout6,parent,false);
            userHolder=new UserHolder();
            userHolder.username=(TextView)row.findViewById(R.id.username);
            userHolder.password=(TextView)row.findViewById(R.id.password);
            userHolder.email=(TextView)row.findViewById(R.id.email);
            row.setTag(userHolder);
        }
        else
        {
            userHolder=(UserHolder)row.getTag();
        }
        User user=(User)this.getItem(position);
        userHolder.username.setText(user.getUserName());
        userHolder.password.setText(user.getPassword());
        userHolder.email.setText(user.getEmail());
        return row;
    }
    static class UserHolder{
        TextView username,password,email;
    }
}

