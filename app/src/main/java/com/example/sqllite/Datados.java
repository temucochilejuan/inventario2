package com.example.sqllite;
/**<!-- Juan Gallegos -->*/

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**<!-- Juan Gallegos -->*/

public class Datados extends BaseAdapter {

    ArrayList<Contacto> lista;
    daoCon dao;
    Contacto c;
    Activity a; // una referencia a la actividad que utiliza este adaptador
    int id = 0; // una variable que almacena temporalmente el ID del contacto seleccionado

    public Datados(Activity a, ArrayList<Contacto> lista, daoCon dao) {
        this.lista = lista;
        this.dao = dao;
        this.a = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        c = lista.get(i);
        return c.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        c = lista.get(posicion);
        TextView nombre_producto = v.findViewById(R.id.txtNombreProducto);
        TextView CodigoBarra = v.findViewById(R.id.txtCodigoBarra);
        TextView Precio = v.findViewById(R.id.txtPrecio);
        TextView Stock = v.findViewById(R.id.txtStock);
        TextView Descripcion = v.findViewById(R.id.et_Descripcion);
        Button editar = v.findViewById(R.id.btnEditar);
        Button eliminar = v.findViewById(R.id.btnEliminar);
        Button detalles = v.findViewById(R.id.btnDetalles);

        nombre_producto.setText(c.getproducto());
        CodigoBarra.setText(c.getCodigoBarra());
        Precio.setText(c.getPrecio());
        Stock.setText(c.getStock());
        Descripcion.setText(c.getDescripcion());
/**<!-- Juan Gallegos -->*/

        editar.setTag(posicion);
        eliminar.setTag(posicion);
        detalles.setTag(posicion);

        if (editar != null) {
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = Integer.parseInt(view.getTag().toString());
                    final Dialog dialog = new Dialog(a);
                    dialog.setTitle("Editar registro");
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.dialogo);
                    dialog.show();
                    final EditText NombreProducto = dialog.findViewById(R.id.et_NombreProducto);
                    final EditText aCodigoBarra = dialog.findViewById(R.id.et_CodigoBarra);
                    final EditText Precio = dialog.findViewById(R.id.et_Precio);
                    final EditText Stock = dialog.findViewById(R.id.et_Stock);
                    final EditText Descripcion = dialog.findViewById(R.id.et_Descripcion);
                    Button guardar = dialog.findViewById(R.id.btnAgregarProducto);
                    Button cancelar = dialog.findViewById(R.id.btnCancelar);
                    c = lista.get(pos);
                    setId(c.getId());
                    NombreProducto.setText(c.getproducto());
                    aCodigoBarra.setText(c.getCodigoBarra());
                    Precio.setText(c.getPrecio());
                    Stock.setText(c.getStock());
                    Descripcion.setText(c.getDescripcion());
                    guardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                c = new Contacto(getId(), NombreProducto.getText().toString(),
                                        aCodigoBarra.getText().toString(),
                                        Precio.getText().toString(),
                                        Stock.getText().toString(),
                                        Descripcion.getText().toString());
                                dao.editar(c);
                                lista = dao.verTodo();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            } catch (Exception e) {
                                Toast.makeText(a, "Error al guardar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    cancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            });
        } else {
            Log.e("Adaptador", "editar es nulo");
        }

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                c = lista.get(pos);
                setId(c.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("¿Estás seguro?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                del.show();
            }
        });

        detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                mostrarDetalles(pos);
            }
        });
        return v;
    }
    /**<!-- Juan Gallegos -->*/

    private void mostrarDetalles(int posicion) {
        c = lista.get(posicion);

        // Crear un diálogo personalizado
        final Dialog dialog = new Dialog(a);
        dialog.setTitle("Detalles del Contacto");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogo);

        final EditText NombreProducto = dialog.findViewById(R.id.et_NombreProducto);
        final EditText CodigoBarra = dialog.findViewById(R.id.et_CodigoBarra);
        final EditText Precio = dialog.findViewById(R.id.et_Precio);
        final EditText Stock = dialog.findViewById(R.id.et_Stock);
        final EditText Descripcion = dialog.findViewById(R.id.et_Descripcion);
        Button aceptar = dialog.findViewById(R.id.btnAgregarProducto);
        Button cancelar = dialog.findViewById(R.id.btnCancelar);

        NombreProducto.setText(c.getproducto());
        CodigoBarra.setText(c.getCodigoBarra());
        Precio.setText(c.getPrecio());
        Stock.setText(c.getStock());
        Descripcion.setText(c.getDescripcion());

        // Configurar las vistas como no editables
        NombreProducto.setEnabled(false);
        CodigoBarra.setEnabled(false);
        Precio.setEnabled(false);
        Stock.setEnabled(false);
        Descripcion.setEnabled(false);

        aceptar.setVisibility(View.GONE);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
/**<!-- Juan Gallegos -->*/

        dialog.show();
    }
}
