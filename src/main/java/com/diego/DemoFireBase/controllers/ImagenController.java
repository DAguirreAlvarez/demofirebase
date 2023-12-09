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

    @GetMapping("/{rutaImagen}")
    public ResponseEntity<Resource> retornarImagen(@PathVariable("rutaImagen")String rutaImagen){
        Resource imagen = new FileSystemResource(uploadDir+ rutaImagen);
        String extension = "image/"+FilenameUtils.getExtension(rutaImagen);
        // System.out.println(imagen);
        // System.out.println(extension);
        if (imagen.exists() && imagen.isReadable()){
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(extension)).body(imagen);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public boolean actualizarImagenArticulo(@PathVariable("id") Long id, @RequestParam("imagen")MultipartFile imagen) throws IOException {
        return articuloService.actualizarImagenArticulo(imagen, id);
    }

}
