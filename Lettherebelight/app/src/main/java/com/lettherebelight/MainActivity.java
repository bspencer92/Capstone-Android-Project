package com.lettherebelight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private EditText editTxtRegisteredEmail, editTxtRegisteredPassword;
    private TextView txtViewForgotPassword, txtViewRegister;
    private Button btnLogin;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBarLaunchScreen);
        editTxtRegisteredEmail = findViewById(R.id.editTxtRegisteredEmail);
        editTxtRegisteredPassword = findViewById(R.id.editTxtRegisteredPassword);
        txtViewForgotPassword = findViewById(R.id.txtViewForgotPassword);
        txtViewRegister = (TextView) findViewById(R.id.txtViewRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txtViewRegister.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtViewRegister:{
                startActivity(new Intent(this, CreateNewUser.class));
                break;
            }
            case R.id.btnLogin:{
                attemptLogin(); 
                break;
            }
        }
    }

    private void attemptLogin() {
        String email = editTxtRegisteredEmail.getText().toString().trim();
        String password = editTxtRegisteredPassword.getText().toString().trim();
        if(email.isEmpty()){
            editTxtRegisteredEmail.setError("Email is required");
            editTxtRegisteredEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTxtRegisteredEmail.setError("Please provide a valid email");
            editTxtRegisteredEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTxtRegisteredPassword.setError("Password is required");
            editTxtRegisteredPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTxtRegisteredPassword.setError("Password must contain 6 characters");
            editTxtRegisteredPassword.requestFocus();
        }


        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    //User user = new User(fullName, email, password);
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SetUpAccount.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}