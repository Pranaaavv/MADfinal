package com.example.madfinal;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.ls.LSInput;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView LSignup;
    private EditText user_name, pass_word;
    private Button LoginBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LSignup = (TextView) findViewById(R.id.LSignup);
        LSignup.setOnClickListener(this);

        LoginBtn=(Button) findViewById(R.id.btnlogin);
        LoginBtn.setOnClickListener(this);

        user_name=(EditText)findViewById(R.id.Lemail);
        pass_word=(EditText) findViewById(R.id.Lpassword);

        mAuth=FirebaseAuth.getInstance();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LSignup:
                startActivity(new Intent(this,Registration.class));
                break;
            case  R.id.btnlogin:
                userLogin();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
//                new Fragment1()).commit();

        }


    }

    private void userLogin() {
        String email = user_name.getText().toString().trim();
        String password= pass_word.getText().toString().trim();

        if (email.isEmpty()) {
            user_name.setError("Email is empty");
            user_name.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            user_name.setError("Enter the valid email");
            user_name.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            pass_word.setError("Password is empty");
            pass_word.requestFocus();
            return;
        }
        if (password.length() < 6) {
            pass_word.setError("Length of password is less than 6");
            pass_word.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                startActivity(new Intent(Login.this, Home.class));

//                if (task.isSuccessful()) {
//                        startActivity(new Intent(Login.this, Home.class));
//
//                }
//                else {
//                        Toast.makeText(Home.this, "Please Check Your login Credentials",Toast.LENGTH_LONG).show();
//                    }

            }
        });





    }
}






























//
//
//
//
//
//    FirebaseAuth mAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        user_name = findViewById(R.id.mEmailET);
//        pass_word = findViewById(R.id.mPasswordET);
//        //Register Btn
//        btn_sign = (TextView) findViewById(R.id.LSignup);
////
//        Button btn_login = findViewById(R.id.btnlogin);
//
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Task<AuthResult> please_check_your_login_credentials = mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
//
//
//                });
//            }
//
//        });
//        btn_sign.setOnClickListener(v -> startActivity(new Intent(Login.this, Registration.class)));
//    }
//
//





