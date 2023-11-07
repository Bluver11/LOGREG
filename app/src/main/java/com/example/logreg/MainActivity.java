package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private EditText UserName;
    private EditText PassWord;
    private Button LogIn;
    private Button SignUp;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameOrEmail = UserName.getText().toString();
                String password = PassWord.getText().toString();

                if (dbHelper.checkUserByUsername(usernameOrEmail, password)) {
                    Toast.makeText(MainActivity.this, "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();
                    login(usernameOrEmail, password);
                } else {
                    Toast.makeText(MainActivity.this, "Sikertelen bejelentkezés!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


public void login(String username, String password){

}
    public void  init(){
        UserName = findViewById(R.id.username);
        PassWord = findViewById(R.id.password);
        LogIn = findViewById(R.id.logIn);
        SignUp = findViewById(R.id.signUp);


    }
}