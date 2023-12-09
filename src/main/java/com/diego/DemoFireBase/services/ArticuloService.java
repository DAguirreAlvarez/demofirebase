package com.diego.DemoFireBase.services;

import com.diego.DemoFireBase.models.Articulo;
import com.diego.DemoFireBase.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String guardarImagen(MultipartFile file) throws IOException {
        String nombreArchivo = file.getOriginalFilename();
        String rutaArchivo = uploadDir+ nombreArchivo;

        File imagen = new File(rutaArchivo);
        imagen.createNewFile();
        try(FileOutputStream fos = new FileOutputStream(imagen)){
            fos.write(file.getBytes());
        }
        return rutaArchivo;
    }

    public Articulo crearArticulo(Articulo articulo){
        return articuloRepository.save(articulo);
    }

    public Articulo buscarArticulo(Long id){
        return articuloRepository.findById(id).orElse(null);
    }

    public List<Articulo> listarArticulos(){
        return articuloRepository.findAll();
    }

    public boolean borrarArticulo(Long id){
        if (buscarArticulo(id)== null){
            return false;
        }else{
            String ruta = buscarArticulo(id).getRutaImagen();
            articuloRepository.delete(buscarArticulo(id));
            eliminarImagen(ruta);
            return true;
        }
    }



    public boolean actualizarArticulo(Articulo articulo, Long id){
        Articulo articuloEncontrado = buscarArticulo(id);
        if (articuloEncontrado != null){
            if (articulo.getTitulo() != null && !articulo.getTitulo().isEmpty()){
                articuloEncontrado.setTitulo(articulo.getTitulo());
            }
            if (articulo.getDescripcion() != null && !articulo.getDescripcion().isEmpty()){
                articuloEncontrado.setDescripcion(articulo.getDescripcion());
            }
            if (articulo.getRutaImagen() != null && !articulo.getRutaImagen().isEmpty()){
                articuloEncontrado.setRutaImagen(articulo.getRutaImagen());
            }
            if (articulo.getUrl() != null && !articulo.getUrl().isEmpty()){
                articuloEncontrado.setUrl(articulo.getUrl());
            }
            if (articulo.getAutor() != null && !articulo.getAutor().isEmpty()){
                articuloEncontrado.setAutor(articulo.getAutor());
            }
            articuloRepository.save(articuloEncontrado);
            return true;
        }else {
            return false;
        }
    }

    public boolean actualizarImagenArticulo(MultipartFile imagen, Long id) throws IOException {
        Articulo articuloEncontrado = buscarArticulo(id);
        if (articuloEncontrado != null && !imagen.isEmpty()){
            eliminarImagen(articuloEncontrado.getRutaImagen());
            articuloEncontrado.setRutaImagen(guardarImagen(imagen));
            crearArticulo(articuloEncontrado);
            return true;
        }else{
            return false;
        }
    }

    public boolean eliminarImagen(String rutaImagen){
        File imagen = new File(rutaImagen);
        if (imagen.exists()){
            if (imagen.delete()){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

}
