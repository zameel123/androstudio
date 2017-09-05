package com.example.hp.studentapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;


public class NextActivity extends AppCompatActivity implements OnItemSelectedListener {
    EditText t1;Button b1;
    SQLiteDatabase db;
    RadioGroup rb;
    RadioButton ra,m,n;
    CheckBox a,b,c;
    public String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        t1=(EditText)findViewById(R.id.t1);
        b1=(Button)findViewById(R.id.b1);
        rb=(RadioGroup)findViewById(R.id.rb);
        m=(RadioButton)findViewById(R.id.m);
        n=(RadioButton)findViewById(R.id.n);

        a=(CheckBox)findViewById(R.id.a);
        b=(CheckBox)findViewById(R.id.b);
        c=(CheckBox)findViewById(R.id.c);
        db=openOrCreateDatabase("database",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student4("+
                "name TEXT NOT NULL PRIMARY KEY,"+
                "branch TEXT NOT NULL ,"+
                "gender TEXT DEFAULT MALE ,"+
                "web INT NOT NULL CHECK (web IN (0,1)),"+
                "android INT NOT NULL CHECK (android IN (0,1)),"+
                "design INT NOT NULL CHECK (design IN (0,1)));");
                Spinner s = (Spinner) findViewById(R.id.t2);
                s.setOnItemSelectedListener(this);
                List list=new ArrayList();
                list.add("");
                list.add("CIVIL ENGG");
                list.add("CHEMICAL ENGG");
                list.add("COMPUTER SC & ENGG");
                list.add("ELECTRONICS & COMM ENGG");
                list.add("ELECTRICAL & ELECTRONICS ENGG");
                list.add("MECHANICAL ENGG");
                list.add("MECHANICAL(prodn)ENGG");
                list.add("B ARCH");
                ArrayAdapter d=new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
                d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(d);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      int ce=rb.getCheckedRadioButtonId();
                                      ra=(RadioButton)findViewById(ce);
                                      String nam=t1.getText().toString();
                                      Cursor resultSet = db.rawQuery("SELECT * from student4 WHERE name=?",
                                              new String[]{nam});
                                      resultSet.moveToFirst();
                                     if(!resultSet.isAfterLast()){
                                          AlertDialog.Builder build = new AlertDialog.Builder(NextActivity.this);
                                          build.setTitle("DUPLICATION");
                                          build.setMessage("already exist");
                                          build.setCancelable(true);
                                          build.setIcon(R.mipmap.ic_launcher);
                                          build.show();
                                          resultSet.close();
                                          return;
                                      }
                                      String gend=ra.getText().toString();

                                      String br=i;
                                      Boolean ac=a.isChecked();
                                      Boolean bc=b.isChecked();
                                      Boolean cc=c.isChecked();
                                      int st,su,sv;
                                      st=ac?1:0;
                                      su=bc?1:0;
                                      sv=cc?1:0;
                                          db.execSQL("INSERT INTO student4 VALUES(" + "'" + nam + "'" + ",'" + br + "','" + gend + "'," + st + "," + su + "," + sv + ");");
                                          Toast.makeText(getApplicationContext(), "Values stored", Toast.LENGTH_LONG).show();
                                  }


                              }
        );
           }
    @Override
    public void onBackPressed(){
       super. onBackPressed();
    }
            @Override
            public  void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
                  i = parent.getItemAtPosition(position).toString();}
            public void onNothingSelected(AdapterView<?> arg0){

            }



}
