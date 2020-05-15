package com.example.voting.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.voting.Listdisplay2;
import com.example.voting.R;
import java.util.ArrayList;
import java.util.List;
 import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Questiondisplay extends ArrayAdapter{
    static List list = new ArrayList();
    public Questiondisplay(@NonNull Listdisplay2 context, int resource) {
        super(context, resource);
    }
    public static void add(Question object)
    {
        //super.add(object);
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;
        row=convertView;
        QuesHolder quesHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.rowlayout,parent,false);
            quesHolder=new QuesHolder();
            quesHolder.id=(TextView)row.findViewById(R.id.i);
            quesHolder.question=(TextView)row.findViewById(R.id.question);
            quesHolder.ans1=(TextView)row.findViewById(R.id.ans1);
            quesHolder.ans2=(TextView)row.findViewById(R.id.ans2);
            quesHolder.ans3=(TextView)row.findViewById(R.id.ans3);
            row.setTag(quesHolder);
        }
        else
        {
            quesHolder=(QuesHolder)row.getTag();
        }
        Question question=(Question)this.getItem(position);
        quesHolder.id.setText(Integer.toString(question.getId()));
        quesHolder.question.setText(question.getQuestion());
        quesHolder.ans1.setText(question.getAns1());
        quesHolder.ans2.setText(question.getAns2());
        quesHolder.ans3.setText(question.getAns3());
        return row;
    }
    static class QuesHolder{
        TextView id,question,ans1,ans2,ans3;
    }
}
