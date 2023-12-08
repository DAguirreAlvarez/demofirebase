package com.diego.DemoFireBase.controllers;

import com.diego.DemoFireBase.models.Articulo;
import com.diego.DemoFireBase.services.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Articulo")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @PostMapping
    public Articulo guardarArticulo(@RequestBody Articulo articulo){
        return articuloService.crearArticulo(articulo);
    }

    @GetMapping("/{id}")
    public Articulo buscarArticulo(@PathVariable("id")Long id){
        return articuloService.buscarArticulo(id);
    }

    @GetMapping
    public List<Articulo> listarArticulos(){
        return articuloService.listarArticulos();
    }

    @DeleteMapping("/{id}")
    public boolean eliminarArticulo(@PathVariable("id")Long id){
        return articuloService.borrarArticulo(id);
    }

    @PutMapping("/{id}")
    public boolean actualizarArticulo(@PathVariable("id")Long id, @RequestBody Articulo articulo){
        return articuloService.actualizarArticulo(articulo, id);
    }

}
