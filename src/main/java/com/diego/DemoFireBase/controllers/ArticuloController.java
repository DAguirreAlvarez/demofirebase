package com.diego.DemoFireBase.controllers;

import com.diego.DemoFireBase.models.Articulo;
import com.diego.DemoFireBase.services.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Articulo")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @PostMapping
    public ResponseEntity<String> guardarArticulo(
            @RequestParam("imagen")MultipartFile imagen,
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("autor") String autor,
            @RequestParam("url") String url
            ){
        try{
            String rutaImg = articuloService.guardarImagen(imagen);
            Articulo articulo = new Articulo(titulo, descripcion, autor, rutaImg, url);
            articuloService.crearArticulo(articulo);

            return ResponseEntity.ok("Articulo creado - "+ imagen.getOriginalFilename());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Articulo no creado");
        }
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
