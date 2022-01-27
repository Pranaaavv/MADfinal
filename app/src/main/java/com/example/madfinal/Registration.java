package com.example.madfinal;



import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;





public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmail, mPassword, mName;
    private Button mRegisterBtn;
//    private ProgressBar progressBar;
    private RadioButton mRadio;
    private ImageView banner;

    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        mRegisterBtn = (Button) findViewById(R.id.mRegisterBtn);
        mRegisterBtn.setOnClickListener(this);

        mName = (EditText) findViewById(R.id.mNameET);
        mEmail = (EditText) findViewById(R.id.mEmailET);
        mPassword = (EditText) findViewById(R.id.mPasswordET);
        mRadio = (RadioButton) findViewById(R.id.mRadiobBtn);
        banner=(ImageView) findViewById(R.id.banner);

//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.mRegisterBtn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String name = mName.getText().toString().trim();
        Integer coordinator = mRadio.getInputType();


        if (name.isEmpty()) {
            mName.setError("Enter the Name");
            mName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            mEmail.setError("Email is empty");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Enter the valid email address");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            mPassword.setError("Enter the password");
            mPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            mPassword.setError("Length of the password should be more than 6");
            mPassword.requestFocus();
            return;
        }


//        progressBar.setVisibility(View.GONE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserHelperClass helperClass = new UserHelperClass(name, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registration.this, "You are successfully Registered", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(Registration.this, "You are not Registered!", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);

                                    }

                                }
                            });
                        }
                    }
                });


    }
}





