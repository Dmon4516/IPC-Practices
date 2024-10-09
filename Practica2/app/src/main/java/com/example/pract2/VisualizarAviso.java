package com.example.pract2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class VisualizarAviso extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_aviso);

        Intent intent = getIntent();
        String mensajeNombre = intent.getStringExtra("Cnombre");
        String mensajeApellidos = intent.getStringExtra("Capellidos");
        String mensajeDomicilio = intent.getStringExtra("Cdomicilio");
        String mensajeDNI = intent.getStringExtra("Cdni");
        String mensajeEmail = intent.getStringExtra("Cemail");
        String mensajePassword = intent.getStringExtra("Cpassword");
        String mensajeIBAN = intent.getStringExtra("Ciban");

        ((TextView)findViewById(R.id.vi_nombre)).setText(String.format("Nombre: %s", mensajeNombre));
        ((TextView)findViewById(R.id.vi_apellidos)).setText(String.format("Apellidos: %s", mensajeApellidos));
        ((TextView)findViewById(R.id.vi_domicilio)).setText(String.format("Domicilio: %s", mensajeDomicilio));
        ((TextView)findViewById(R.id.vi_dni)).setText(String.format("DNI: %s", mensajeDNI));
        ((TextView)findViewById(R.id.vi_email)).setText(String.format("Email: %s", mensajeEmail));
        ((TextView)findViewById(R.id.vi_password)).setText(String.format("Password: %s", mensajePassword));
        ((TextView)findViewById(R.id.vi_iban)).setText(String.format("IBAN: %s", mensajeIBAN));

    }
}