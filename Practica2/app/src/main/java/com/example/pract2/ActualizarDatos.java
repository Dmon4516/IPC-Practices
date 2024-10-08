package com.example.pract2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ActualizarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actualizar_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void modificarDatos (View view) {
        Intent lanzadorAviso = new Intent(this, VisualizarAviso.class);
        lanzadorAviso.setAction(Intent.ACTION_SEND);
        lanzadorAviso.putExtra("CNombre",((TextInputEditText) (findViewById(R.id.nombre))).getText().toString());
        lanzadorAviso.putExtra("CApellidos", ((TextInputEditText) (findViewById(R.id.apellidos))).getText().toString());
        lanzadorAviso.putExtra("CDomicilio", ((TextInputEditText) (findViewById(R.id.domicilio))).getText().toString());
        lanzadorAviso.putExtra("CDNI", ((TextInputEditText) (findViewById(R.id.dni))).getText().toString());
        lanzadorAviso.putExtra("CEmail", ((TextInputEditText) (findViewById(R.id.email))).getText().toString());
        lanzadorAviso.putExtra("CPassword", ((TextInputEditText) (findViewById(R.id.password))).getText().toString());
        lanzadorAviso.putExtra("CIban", ((TextInputEditText) (findViewById(R.id.iban))).getText().toString());
        startActivity(lanzadorAviso);
    }
}