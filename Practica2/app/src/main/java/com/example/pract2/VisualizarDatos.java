package com.example.pract2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class VisualizarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_datos);

        Intent intent = getIntent();

        String mensajeNombre = intent.getStringExtra("CNombre");
        String mensajeApellidos = intent.getStringExtra("CApellidos");
        String mensajeDomicilio = intent.getStringExtra("CDomicilio");
        String mensajeDNI = intent.getStringExtra("CDNI");
        String mensajeEmail = intent.getStringExtra("CEmail");
        String mensajePassword = intent.getStringExtra("CPassword");
        String mensajeIBAN = intent.getStringExtra("CIban");

        ((TextView)findViewById(R.id.vi_nombre)).setText(String.format("Nombre: %s", mensajeNombre));
        ((TextView)findViewById(R.id.vi_apellidos)).setText(String.format("Apellidos: %s", mensajeApellidos));
        ((TextView)findViewById(R.id.vi_domicilio)).setText(String.format("Domicilio: %s", mensajeDomicilio));
        ((TextView)findViewById(R.id.vi_dni)).setText(String.format("DNI: %s", mensajeDNI));
        ((TextView)findViewById(R.id.vi_email)).setText(String.format("Email: %s", mensajeEmail));
        ((TextView)findViewById(R.id.vi_password)).setText(String.format("Password: %s", mensajePassword));
        ((TextView)findViewById(R.id.vi_iban)).setText(String.format("IBAN: %s", mensajeIBAN));
    }

    public void lanzarActividad (View view) {
        Intent lanzadorActividad = new Intent(this, Menu.class);
        startActivity(lanzadorActividad);
    }
}