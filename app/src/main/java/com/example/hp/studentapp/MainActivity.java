package com.example.hp.studentapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import android.widget.AdapterView;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    ListView l;
    ArrayList<String> sl=new ArrayList<>();
    ArrayAdapter studapter;
    Button AddStudent;
    SQLiteDatabase d;
    SharedPreferences sh;
    public int z,i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh=getSharedPreferences("demo",MODE_PRIVATE);
        l=(ListView)findViewById(R.id.studlist);
        d=openOrCreateDatabase("database",MODE_PRIVATE,null);

        studapter=new ArrayAdapter<>(this,R.layout.studlist,sl);

        l.setAdapter(studapter);
        AddStudent=(Button)findViewById(R.id.AddStud);

        AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d= String.valueOf(z);
                Toast.makeText(MainActivity.this, "NUMBER OF STUDENTS REGISTERED:"+d, Toast.LENGTH_LONG).show();
                Intent l = new Intent(MainActivity.this, NextActivity.class);
                startActivity(l);


            }
        });

        l=(ListView)findViewById(R.id.studlist);

        studapter=new ArrayAdapter(this,R.layout.studlist,sl);
        l.setAdapter(studapter);
        d.execSQL("CREATE TABLE IF NOT EXISTS student4("+
                "name TEXT NOT NULL PRIMARY KEY,"+
                "branch TEXT NOT NULL,"+
                "gender TEXT NOT NULL ,"+
                "web INT NOT NULL CHECK (web IN (0,1)),"+
                "android INT NOT NULL CHECK (android IN (0,1)),"+
                "design INT NOT NULL CHECK (design IN (0,1)));");
            Cursor resultSet = d.rawQuery("SELECT * from student4",null);
            resultSet.moveToFirst();
            while(!resultSet.isAfterLast()){
                i=i+1;
                String n = (resultSet.getString((0)));
                String b = ((resultSet.getString(1)));
                String r = n+"\n"+b;
                sl.add(r);
                resultSet.moveToNext();
            }
        studapter=new ArrayAdapter(this,R.layout.studlist,sl);
        l.setAdapter(studapter);

                z = sh.getInt("count", 0);
                if (i > z) {
                    z = z + 1;
                    sh.edit().putInt("count", z).apply();
                }
                resultSet.close();
         l.setOnItemClickListener(new OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                 Bundle b = new Bundle();
                 b.putInt("pos",pos);
                 Intent intent = new Intent(MainActivity.this, details.class);
                 intent.putExtra("pos",pos);
                 startActivity(intent);
             }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;

    }
    boolean bp=false;
    @Override
    public void onBackPressed(){
        if(bp)
        {
            super.onBackPressed();
        }
        else{
            int t=1000;
            bp=true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bp=false;
                }
            },t);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.c2:
                Intent q = new Intent(MainActivity.this, web.class);
                startActivity(q);

                return true;
            case R.id.c1:
                Toast.makeText(MainActivity.this, "P.A MOHAMED ZAMEEL", Toast.LENGTH_LONG).show();
                return true;

            default:return onOptionsItemSelected(item);

}}
}