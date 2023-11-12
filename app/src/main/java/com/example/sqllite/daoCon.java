package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
//<--juan gallegos suazo-->
public class daoCon {

    SQLiteDatabase bd;//Intancea la base de datos
    ArrayList<Contacto>lista = new ArrayList<Contacto>();//almacena una lista de objetos de tipo contacto
    Contacto c;//Instncia de la clase contacto, utilizada para operaciones de busqueda
    Context ct; //contexto de la aplicacion
    String nombreBD = "BDContactos";//nombre de la base de datos
    //<--juan gallegos suazo-->
    //Cadena de sentencia sql que crea  la tabla de contacto si no existe, define las columnas de las tablas
    String tabla = "CREATE TABLE IF NOT EXISTS contacto(id integer PRIMARY KEY AUTOINCREMENT, producto text, codigo text, precio text, stock text, descripcion text)";

    public daoCon(Context c){
    //contructor de la clase, acepta un parametro de tipo context y se utiliza para inicializar la clase
        this.ct = c;
        bd = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE,null);
        bd.execSQL(tabla);
    }
    public boolean insertar (Contacto c){
        //metodo para insertar un nuevo contacto a la base de datos
        ContentValues contenedor = new ContentValues();
        contenedor.put("producto",c.getproducto());
        contenedor.put("codigo",c.getCodigoBarra());
        contenedor.put("precio",c.getStock());
        contenedor.put("stock",c.getPrecio());
        contenedor.put("descripcion",c.getDescripcion());
        return (bd.insert("inventario",null, contenedor))>0;
    }
    public boolean eliminar(int id){
        return (bd.delete("inventario", "id=" + id, null)) > 0;
    }
    public boolean editar (Contacto c){
        //metodo para editar la informacion de contacto en la base de datos
        ContentValues contenedor = new ContentValues();
        contenedor.put("producto",c.getproducto());
        contenedor.put("codigo",c.getCodigoBarra());
        contenedor.put("precio",c.getStock());
        contenedor.put("stock",c.getPrecio());
        contenedor.put("descripcion",c.getDescripcion());
        return (bd.update("inventario", contenedor, "id=" + c.getId(), null)) > 0;
        //<--juan gallegos suazo-->
    }
    public ArrayList<Contacto>verTodo(){
        //metodo para recurar una lista de contactos de una base de datos
        lista.clear();
        Cursor cursor = bd.rawQuery("SELECT * FROM contacto",null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Contacto(cursor.getInt(0),
                        cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return lista;
    }
    public Contacto verUno(int posicion){
        //metodo para buscar el contacto segun su id en la base de datos y devolverlo como un obejto contacto
        Cursor cursor = bd.rawQuery("SELECT * FROM contacto",null);
        cursor.moveToPosition(posicion);
        c = new Contacto(cursor.getInt(0),
                cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4),
                cursor.getString(5));
        return c;
    }
}
//<--juan gallegos suazo-->