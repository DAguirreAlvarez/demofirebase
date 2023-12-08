package com.diego.DemoFireBase.services;

import com.diego.DemoFireBase.models.Articulo;
import com.diego.DemoFireBase.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

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
            articuloRepository.delete(buscarArticulo(id));
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

}