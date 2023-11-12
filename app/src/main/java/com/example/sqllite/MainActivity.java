package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;  // Agrega esta importación

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     * <!-- Juan Gallegos -->
     */

    daoCon dao;
    Datados adapter;
    ArrayList<Contacto> lista;
    Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**<!-- Juan Gallegos -->*/

        dao = new daoCon(MainActivity.this);
        lista = dao.verTodo();
        adapter = new Datados(this, lista, dao);
        ListView list = findViewById(R.id.listaV);

        // Obtener el nombre del usuario
        Intent intent = getIntent();
        if (intent != null) {
            String nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO");
            // Haz lo que necesites con el nombre de usuario, por ejemplo, mostrarlo en un TextView
            TextView txtUser = findViewById(R.id.txtUser);
            txtUser.setText("Bienvenido, " + nombreUsuario);

            Button insertar = findViewById(R.id.btnInsertar);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //dialogo para ver vista previa de registro
                    int posicion = i;
                    Contacto contacto = dao.verUno(posicion);
                    // Realizar las acciones necesarias con el contacto seleccionado
                }
            });

            insertar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Dialogo para agregar
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setTitle("Nuevo Registro");
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.dialogo);

                    final EditText NombreProducto = dialog.findViewById(R.id.et_NombreProducto);
                    final EditText CodigoBarra = dialog.findViewById(R.id.et_CodigoBarra);
                    final EditText Stock = dialog.findViewById(R.id.et_Stock);
                    final EditText Precio = dialog.findViewById(R.id.et_Precio);
                    final EditText Descripcion = dialog.findViewById(R.id.et_Descripcion);
                    Button guardar = dialog.findViewById(R.id.btnAgregarProducto);

                    guardar.setText("Agregar");

                    guardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String nombreStr = NombreProducto.getText().toString();
                            String apellidoStr = CodigoBarra.getText().toString();
                            String emailStr = Stock.getText().toString();
                            String telefonoStr = Precio.getText().toString();
                            String ciudadStr = Descripcion.getText().toString();

                            // Validar que al menos un campo tenga información
                            if (!nombreStr.isEmpty() || !apellidoStr.isEmpty() || !emailStr.isEmpty() || !telefonoStr.isEmpty() || !ciudadStr.isEmpty()) {
                                c = new Contacto(nombreStr, apellidoStr, emailStr, telefonoStr, ciudadStr);
                                dao.insertar(c);
                                lista = dao.verTodo();
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Por favor, complete al menos un campo.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Button cancelar = dialog.findViewById(R.id.btnCancelar);
                    cancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss(); // Cierra el diálogo
                        }
                    });

                    // Mostrar el diálogo después de configurarlo
                    dialog.show();
                }
                /**<!-- Juan Gallegos -->*/

            });
        }
    }
}
