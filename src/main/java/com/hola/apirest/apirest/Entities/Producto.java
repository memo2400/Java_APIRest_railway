package com.hola.apirest.apirest.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Son los objetos que represnetan , y encapsulan 
 * datos y comportamientos , de los objetos, que
 * depues seran mapeados con la base de datos 
 * 
 * este sera nuestro nuevo primer crud
 * que es crear leer, actualizar y borrar 
 */

//import jakarta.annotation.Generated;

/*
 * con esto , java sabra que esto es una entidad
 * son los decoradores, de spring
 */
@Entity
public class Producto {

    /*
     * Este ID sera unicoo para cada producto 
     * y sera ascendente , automatico
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    
    /*
     * hacemos la feneracion de getters y setters
     * de forma massiva
     * 
     */

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
