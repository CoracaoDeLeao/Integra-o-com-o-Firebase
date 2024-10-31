package com.fatec.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fatec.login.obj.ProdutosServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class access_approved extends AppCompatActivity {

    private Intent in;
    private EditText FieldID, FieldPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_approved);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        TextView aprovTxt = findViewById(R.id.access_approved);

        if(user == null){
            in = new Intent(access_approved.this, MainActivity.class);
            startActivity(in);
            finish();
        }

        try {
            String displayName = Objects.requireNonNull(user).getDisplayName();
            aprovTxt.setText("Bem-vindo, " + displayName);
        } catch (NullPointerException e) {
            Log.e("Firestore", "Erro: O nome não está disponível.", e);
            Toast.makeText(this, "Erro: nome não encontrado.", Toast.LENGTH_SHORT).show();
            aprovTxt.setText("NULL");
        }
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        in = new Intent(access_approved.this, MainActivity.class);
        startActivity(in);
        finish();
    }

    public void Cadastrar(View view) {
        String idText, priceText;

        FieldID = findViewById(R.id.fieldID);
        FieldPrice = findViewById(R.id.fieldPreco);
        idText = FieldID.getText().toString();
        priceText = FieldPrice.getText().toString();

        if (validarInputs(idText, priceText)) {
            int ID = Integer.parseInt(idText);
            float Price = Float.parseFloat(priceText);

            ProdutosServices prodSer = new ProdutosServices();
            prodSer.cadastrar(ID, Price,this);
        }
    }

    public void Buscar(View view) {
        String idText;

        FieldID = findViewById(R.id.fieldID);
        idText = FieldID.getText().toString();

        TextView idTxt = findViewById(R.id.txtBuscarID);
        TextView precoTxt = findViewById(R.id.txtBuscarPreco);

        if (validarInputs(idText)) {
            int ID = Integer.parseInt(idText);

            ProdutosServices prodSer = new ProdutosServices();
            prodSer.buscar(ID,this, idTxt, precoTxt);
        }
    }

    public void Alterar(View view) {
        String idText, priceText;

        FieldID = findViewById(R.id.fieldID);
        FieldPrice = findViewById(R.id.fieldPreco);
        idText = FieldID.getText().toString();
        priceText = FieldPrice.getText().toString();

        if (validarInputs(idText, priceText)) {
            int ID = Integer.parseInt(idText);
            float Price = Float.parseFloat(priceText);

            ProdutosServices prodSer = new ProdutosServices();
            prodSer.alterar(ID, Price,this);
        }
    }

    public void Deletar(View view) {
        String idText;

        FieldID = findViewById(R.id.fieldID);
        idText = FieldID.getText().toString();

        if (validarInputs(idText)) {
            int ID = Integer.parseInt(idText);

            ProdutosServices prodSer = new ProdutosServices();
            prodSer.deletar(ID,this);
        }
    }

    public boolean validarInputs(String idText, String priceText) {
        int ID;
        float Price;

        // Validação do ID
        if (idText.isEmpty()) {
            Toast.makeText(this, "Enter ID.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            try {
                ID = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid ID format.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // Validação do Preço
        if (priceText.isEmpty()) {
            Toast.makeText(this, "Enter price.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            try {
                Price = Float.parseFloat(priceText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid price format.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true; // Valores válidos
    }

    public boolean validarInputs(String idText) {
        int ID;

        // Validação do ID
        if (idText.isEmpty()) {
            Toast.makeText(this, "Enter ID.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            try {
                ID = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid ID format.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true; // Valores válidos
    }
}
