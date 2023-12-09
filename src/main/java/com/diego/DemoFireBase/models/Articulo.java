package com.diego.DemoFireBase.models;

import com.google.firebase.database.annotations.NotNull;
import jakarta.persistence.*;

@Entity
@Table(name= "Articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String autor;
    // A poder ser las imagenes a ingresar deben de ser en JPEG, sino PNG.
    private String rutaImagen;
    private String url;

    public Articulo(){
    }

    public Articulo(String titulo, String descripcion, String autor, String rutaImagen, String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.rutaImagen = rutaImagen;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
