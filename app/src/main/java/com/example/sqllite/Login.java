package com.example.sqllite;
//<--juan gallegos suazo-->
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//<--juan gallegos suazo-->
public class Login extends AppCompatActivity {
    //<--juan gallegos suazo-->
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void iniciarMainActivity(View view) {
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        String nombreUsuario = editTextNombre.getText().toString().trim().toUpperCase();

        if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
            Intent iniciar = new Intent(Login.this, MainActivity.class);
            iniciar.putExtra("NOMBRE_USUARIO", nombreUsuario);
            startActivity(iniciar);
        } else {
            // Mostrar un mensaje de error indicando que el nombre es obligatorio
            Toast.makeText(this, "Por favor, ingresa un nombre antes de continuar", Toast.LENGTH_SHORT).show();
        }
    }
    //<--juan gallegos suazo-->
    public void Maps(View v) {
        Intent i = new Intent(this, Mapas.class);
        startActivity(i);
        finish();
    }
}
//<--juan gallegos suazo-->