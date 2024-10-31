package com.fatec.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class create_account extends AppCompatActivity {

    private Intent in;
    private FirebaseAuth mAuth;
    private EditText fieldName, fieldEmail, fieldPass, fieldApass;
    private CircularProgressIndicator progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        mAuth = FirebaseAuth.getInstance();

        fieldName = findViewById(R.id.nameInputField);
        fieldEmail = findViewById(R.id.emailInputField);
        fieldPass = findViewById(R.id.passInputField);
        fieldApass = findViewById(R.id.passInputFieldConfirm);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            in = new Intent(create_account.this, access_approved.class);
            startActivity(in);
            finish();
        }
    }

    public void cadastrado(View view) {

        String name, email, password, apass;

        name = String.valueOf(fieldName.getText());
        email = String.valueOf(fieldEmail.getText());
        password = String.valueOf(fieldPass.getText());
        apass = String.valueOf(fieldApass.getText());

        progressBar.setVisibility(View.VISIBLE);

        if(TextUtils.isEmpty(name)){
            Toast.makeText(create_account.this, "Enter name.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(create_account.this, "Enter e-mail.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(create_account.this, "Enter password.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(apass)){
            Toast.makeText(create_account.this, "Confirm the password.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals(apass)){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Usuário registrado com sucesso
                                FirebaseUser user = mAuth.getCurrentUser();

                                if (user != null) {
                                    // Atualizando o nome do usuário
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();
                                    // Manda as a alteração do nome para o firebase
                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(updateTask -> {
                                                if (updateTask.isSuccessful()) {
                                                    Toast.makeText(create_account.this, "Account created.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                            else if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                // If sign in fails, display a message to the user.
                                Toast.makeText(create_account.this, "An account with this email already exists.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(create_account.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(create_account.this, "Use the same passwords.",
                Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {
        in = new Intent(create_account.this, MainActivity.class);
        startActivity(in);
    }
}
