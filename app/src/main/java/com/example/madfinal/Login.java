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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.ls.LSInput;

import java.util.HashMap;
import java.util.Objects;

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
//                startActivity(new Intent(Login.this, Home.class));

//                if (task.isSuccessful()) {
//                        startActivity(new Intent(Login.this, Home.class));
//
//                }
//                else {
//                        Toast.makeText(Home.this, "Please Check Your login Credentials",Toast.LENGTH_LONG).show();
//                    }
                if (task.isSuccessful()) {


                    FirebaseUser user = mAuth.getCurrentUser();

                    if (Objects.requireNonNull(task.getResult().getAdditionalUserInfo()).isNewUser()) {
                        String email = Objects.requireNonNull(user).getEmail();
                        String uid = user.getUid();
                        HashMap<Object, String> hashMap = new HashMap<>();
                        hashMap.put("email", email);
                        hashMap.put("uid", uid);
                        hashMap.put("name", "");
                        hashMap.put("onlineStatus", "online");
                        hashMap.put("typingTo", "noOne");
                        hashMap.put("phone", "");
                        hashMap.put("image", "");
                        hashMap.put("cover", "");
                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        // store the value in Database in "Users" Node
                        DatabaseReference reference = database.getReference("Users");

                        // storing the value in Firebase
                        reference.child(uid).setValue(hashMap);
                    }
                    Toast.makeText(Login.this, "Registered User " + user.getEmail(), Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(Login.this, Home.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                } else {

                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                }







            }
        });
    }
}