package com.si3ri.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity // Entidad de JPA; significa que está mapeada a una tabla en la base de datos.
@Table(name = "autores") // Nombre de la tabla en la base de datos a la que será mapeada.
public class Autores {
    @Id // Clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Valor del campo 'id' que se generará automáticamente utilizando la estrategia de identidad de la base de datos.
    private Long id;
    private String nombre;
    private int nacimiento;
    private int deceso;

    // 'OTM' es la relación de uno a muchos con la entidad 'Libros', donde autor es el campo de la clase 'Libros' que posee la clave externa.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 'fetch = FetchType.EAGER' significa que los libros se cargarán inmediatamente con el autor.
    private List<Libros> libros;
    public Autores() {
    }

    // Constructor sin parámetros requerido por JPA.
    public Autores(String nombre, int nacimiento, int deceso) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.deceso = deceso;
    }

    // Métodos Getter y Setter.
    public List<Libros> getLibros() {
        if (libros == null) {
            libros = new ArrayList<>();
        }
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }
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

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getDeceso() {
        return deceso;
    }

    public void setDeceso(int deceso) {
        this.deceso = deceso;
    }

    @Override
    public String toString() { // Se sobrescribe el método 'toString' para proporcionar una representación de cadena del objeto autor.
        return  "====== AUTOR DEL LIBRO ======" +'\n'+
                "Autor: " +  nombre +'\n'+
                "Fecha de nacimiento: " + nacimiento +'\n'+
                "Fecha de fallecimiento: " + deceso +'\n'+
                "Libros: "+libros.stream()
                .map(l->l.getTitulo())
                .collect(Collectors.toList()) +'\n';

    }
}