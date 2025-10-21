package com.hola.apirest.apirest.Controllers;

import java.util.List;

import com.hola.apirest.apirest.Repositories.Producto_Repository;
import com.hola.apirest.apirest.Entities.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.DeleteMapping;

//import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



/*
 * Pondremos tosas las direcciones
 * para que nuestros clientes consuman datos
 * 
 * Aqui se tiene la logica del negocio, aqui se 
 * devuleve informacion o se devuleve ya procesada
 * 
 * estructura back end que da las urls
 * 
 *  /productos, se ira a la nuestra BD, donde estaran
 * los productos
 */

@RestController
@RequestMapping ("/productos")
 public class ProductoController {

    // hace una instancia del los repos de forma automatica
    // con una sola linea queda todo
    // para ahorrar codigo
    // tal vez el "_" causa error
    @Autowired
    private Producto_Repository productoRepository;


    
    // El JPA nos devuelve todo, todos los productos
    // se consume facil la BD con .findAll()
    @GetMapping
    public List<Producto> getAllProducts(){
        return productoRepository.findAll();
    }

    /*
     * Recibimos un ID
     * Si no encuentra nada hacemos un error
     * Si si lo encuientra lo modificamos
     */
    @GetMapping("/{id}")
    public Producto obtenerProductoPorID(@PathVariable Long id){

        return productoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos el producto cuyo ID es: " + id));
    }

    /*
     * GUARDADO
     * 
     * El request significa que el cliente pedira datos a la BD
     * de nuestra classe producto de carpeta entities, cargaremos 
     * p brindaremos todos los aprametros necesarios exepto el ID
     * Guardaremos todo aqui
     */
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        
        // guardamos el producto que recibimos en el parametro        
        return productoRepository.save(producto);
    }
    
    /*
     * ACTUALIZACION
     * 
     * Si no encuentra nada hacemos un error
     * Si si lo encuientra lo modificamos
     * 
     * Se crea una variable nueva
     * se modifica el precio y nombre por los prametros que llegan
     * guarda al final la variable en nuestro repositorio BD
     */

     @PutMapping("/{id}")
     public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
         
         Producto productoo = productoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos el producto cuyo ID es: " + id));
         
        productoo.setNombre(detallesProducto.getNombre());
        productoo.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(productoo);
     }

     /*
      * Se suele retornar el procuto eliminado, pero esta vez
      regresaremos una frase
      */
    
    @DeleteMapping ("/{id}")
    public String borrarProducto (@PathVariable Long id){
        Producto productoABorrar = productoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo borrar el producto: " + id));

        productoRepository.delete(productoABorrar);
        return "se borro el producto " + id + "FHD";

    }
}



/*
 * http nos manda datos entrre cosas, es el protocolo en que se envia
 *  get = recibe cosas de un server
 *  post = crear un nuevo recursos
 *  put = actualizar o reemplazar versiona anterior
 *  patch = actualizar una parte del producto
 *  delete = para borrar el recurso
 * 
 * 
 * REST brinda los datos en JSON  (api rest = forma en la que trabaja el envio de la informacion)
 * se usa el JSON porque es muy rapido para mandar cosas
 */
