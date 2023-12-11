package com.diego.DemoFireBase.controllers;

import com.diego.DemoFireBase.services.ArticuloService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Articulo/Imagen")
public class ImagenController {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/ruta/{nombreArchivo}")
    public ResponseEntity<Resource> retornarImagen(@PathVariable("nombreArchivo")String nombreArchivo){
        Resource imagen = new FileSystemResource(uploadDir+ nombreArchivo);
        String extension = "image/"+FilenameUtils.getExtension(nombreArchivo);
        // System.out.println(imagen);
        // System.out.println(extension);
        if (imagen.exists() && imagen.isReadable()){
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(extension)).body(imagen);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{idArticulo}")
    public ResponseEntity<Resource> retornarImagenPorIdArticulo(@PathVariable("idArticulo")Long idArticulo){
        String rutaImagen = articuloService.buscarArticulo(idArticulo).getRutaImagen();
        Resource imagen = new FileSystemResource(rutaImagen);
        String extension = "image/"+FilenameUtils.getExtension(rutaImagen);
        if (imagen.exists() && imagen.isReadable()){
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(extension)).body(imagen);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public boolean actualizarImagenArticulo(@PathVariable("id") Long id,
                                            @RequestParam("imagen")MultipartFile imagen) throws IOException {
        return articuloService.actualizarImagenArticulo(imagen, id);
    }

}
