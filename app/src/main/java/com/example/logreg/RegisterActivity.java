package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
private EditText Email;
private EditText Username;
private EditText Password;
private EditText Fullname;
private Button signUp;
private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void init(){
        Email = findViewById(R.id.Email);
        Username = findViewById(R.id.username1);
        Password = findViewById(R.id.password1);
        Fullname = findViewById(R.id.Fullname);
        signUp = findViewById(R.id.signUp1);
        back = findViewById(R.id.back);
    }
}