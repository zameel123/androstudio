package com.example.hp.studentapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class details extends AppCompatActivity {
 SQLiteDatabase d;EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        t=(EditText)findViewById(R.id.t1);
        d=openOrCreateDatabase("database",MODE_PRIVATE,null);
        Bundle b=getIntent().getExtras();
        String name=b.getString("nam");
        int p=b.getInt("pos");
        d.execSQL("CREATE TABLE IF NOT EXISTS student4("+
                "name TEXT NOT NULL PRIMARY KEY,"+
                "branch TEXT NOT NULL,"+
                "gender TEXT NOT NULL ,"+
                "web INT NOT NULL CHECK (web IN (0,1)),"+
                "android INT NOT NULL CHECK (android IN (0,1)),"+
                "design INT NOT NULL CHECK (design IN (0,1)));");
        Cursor r = d.rawQuery("SELECT * from student4 WHERE name=?",new String[]{name});
        r.moveToFirst();
        String s="NAME:\t"+r.getString(0)+"\nBRANCH:\t"+r.getString(1)+"\nGENDER:\t"+r.getString(2)+"\nINTERESTS:\t";
        if(r.getInt(3)==1){
            s+=r.getInt(3)+"\t";
        }
        if(r.getInt(4)==1){
            s+=r.getInt(4)+"\t";
        }
        if(r.getInt(5)==1){
            s+=r.getInt(5)+"\t";
        }
        t.setText(s);
    }
}
