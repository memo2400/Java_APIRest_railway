//package com.hola.apirest.apirest.Repositories;
package com.hola.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hola.apirest.apirest.Entities.Producto;

/*
 * tenemos nuestra interface crada, en ligar de clase
 * 
 * El repositorio es para poder interactuar con la
 * base de datos, borrar , escribir, y actualizar, 
 * es la persistencia
 */

 //                 <tipo de entidad, tipo variable>
 public interface Producto_Repository extends JpaRepository <Producto, Long> {

}


// 06:40:14

// 06 : 47 : 00