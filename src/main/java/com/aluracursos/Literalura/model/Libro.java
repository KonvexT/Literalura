package com.aluracursos.Literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Integer descargas;

    @ManyToOne
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.descargas = datosLibro.descargas();

        if (datosLibro.idiomas() != null && !datosLibro.idiomas().isEmpty()) {
            this.idioma = datosLibro.idiomas().get(0);
        } else {
            this.idioma = "desconocido";
        }
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro: " + titulo +
                " | Idioma: " + idioma +
                " | Descargas: " + descargas +
                " | Autor: " + (autor != null ? autor.getNombre() : "desconocido");
    }
}
