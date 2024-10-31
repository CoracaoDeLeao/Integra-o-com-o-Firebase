package com.fatec.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class reset_password extends AppCompatActivity {

    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

    }

    public void login(View view) {
        in = new Intent(reset_password.this, MainActivity.class);
        startActivity(in);
    }

    public void send(View view) {
        EditText fieldEmail;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        fieldEmail = findViewById(R.id.emailInputField);
        String email = String.valueOf(fieldEmail.getText());

        if(TextUtils.isEmpty(email)){
            Toast.makeText(reset_password.this, "Enter e-mail.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // E-mail de redefinição de senha enviado com sucesso
                        Toast.makeText(reset_password.this, "Se o e-mail fornecido estiver associado a uma conta, você receberá um e-mail de redefinição de senha.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Falha ao enviar o e-mail
                        Toast.makeText(reset_password.this, "Erro ao enviar o e-mail de redefinição.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

