package com.example.sqllite;
/**<!-- Juan Gallegos -->*/
//<--juan gallegos suazo-->
public class Contacto {

    int id;
    String nombre_producto ;
    String CodigoBarra ;
    String Precio ;
    String Stock ;
    String Descripcion ;
    /**<!-- Juan Gallegos -->*/

    public Contacto() {
    }

    public Contacto(String nombre_producto , String CodigoBarra , String Precio , String Stock , String Descripcion ) {
        this.nombre_producto  = nombre_producto ;
        this.CodigoBarra  = CodigoBarra ;
        this.Precio  = Precio ;
        this.Stock  = Stock ;
        this.Descripcion  = Descripcion ;
    }
    //<--juan gallegos suazo-->
    public Contacto(int id, String nombre_producto , String CodigoBarra , String Precio , String Stock , String Descripcion ) {
        this.id = id;
        this.nombre_producto  = nombre_producto ;
        this.CodigoBarra  = CodigoBarra ;
        this.Precio  = Precio ;
        this.Stock  = Stock ;
        this.Descripcion  = Descripcion ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //<--juan gallegos suazo-->
    public String getproducto() {
        return nombre_producto ;
    }

    public void setNombre(String nombre) {
        this.nombre_producto  = nombre_producto;
    }

    public String getCodigoBarra() {
        return CodigoBarra ;
    }
    /**<!-- Juan Gallegos -->*/
    /**<!-- Juan Gallegos -->*/

    public void setApellido(String apellido) {
        this.CodigoBarra  = CodigoBarra;
    }
    /**<!-- Juan Gallegos -->*/

    public String getPrecio() {
        return Precio ;
    }

    public void setTelefono(String telefono) {
        this.Precio  = Precio;
    }

    public String getStock() {
        return Stock ;
    }
    /**<!-- Juan Gallegos -->*/

    public void setEmail(String email) {
        this.Stock  = Stock;
    }
    //<--juan gallegos suazo-->
    public String getDescripcion() {
        return Descripcion ;
    }

    public void setCiudad(String ciudad) {
        this.Descripcion  = Descripcion ;
    }
}
/**<!-- Juan Gallegos -->*/
