package com.fatec.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Intent in;
    private FirebaseAuth mAuth;
    private EditText fieldPass, fieldEmail;
    private CircularProgressIndicator progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        fieldEmail = findViewById(R.id.emailInputField);
        fieldPass = findViewById(R.id.passInputField);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            in = new Intent(MainActivity.this, access_approved.class);
            startActivity(in);
            finish();
        }
    }

    public void entrar(View view) {
        // Chama o método assíncrono de login
        loginAsync();
    }

    private void loginAsync() {
        String email, password;

        FirebaseUser currentUser = mAuth.getCurrentUser();

        email = String.valueOf(fieldEmail.getText());
        password = String.valueOf(fieldPass.getText());

        if(TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "Enter e-mail.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Enter password.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Exibe o indicador de progresso
        progressBar.setVisibility(View.VISIBLE);

        Task<AuthResult> loginTask = mAuth.signInWithEmailAndPassword(email, password);

        loginTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            in = new Intent(MainActivity.this, access_approved.class);
                            startActivity(in);
                        }
                        else if(task.getException() instanceof FirebaseAuthInvalidUserException || task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Incorrect email or password.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void criar(View view) {
        in = new Intent(MainActivity.this, create_account.class);
        startActivity(in);
    }

    public void resetPass(View view) {
        in = new Intent(MainActivity.this, reset_password.class);
        startActivity(in);
    }
}