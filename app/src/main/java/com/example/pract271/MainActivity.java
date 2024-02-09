package com.example.pract271;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DbHandler db;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DbHandler(this);

        t1=findViewById(R.id.email);
        t2=findViewById(R.id.pass);


        db.insertData("UK@gmail.com","abcd");
    }
    public void Login(View v) {
        String email = t1.getText().toString();
        String pass = t2.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Fill All the Fields!", Toast.LENGTH_SHORT).show();
        } else {
            String e = db.CheckEmail(email);
            String p = db.CheckPass(email, pass);
            if (e != null && p != null && e.equals(email) && p.equals(pass)) {
                Toast.makeText(this, "Successfully Log-in", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Email id or Password", Toast.LENGTH_SHORT).show();
            }
        }
    }

}