package com.aluracursos.Literalura.principal;



import com.aluracursos.Literalura.model.*;
import com.aluracursos.Literalura.repository.AutorRepository;
import com.aluracursos.Literalura.repository.LibroRepository;
import com.aluracursos.Literalura.service.ConsumoAPI;
import com.aluracursos.Literalura.service.ConvierteDatos;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private AutorRepository autorRepository;
    private LibroRepository libroRepository;

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void muestraElMenu() {
        int opcion = -1;
        //LO SAQUE 100% CON IA JAJAJAJA
        while (opcion != 0) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          LITERALURA MENÚ             ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1 - Buscar libro por título         ║");
            System.out.println("║  2 - Listar libros registrados       ║");
            System.out.println("║  3 - Listar autores registrados      ║");
            System.out.println("║  4 - Listar autores vivos en un año  ║");
            System.out.println("║  5 - Listar libros por idioma        ║");
            System.out.println("║  0 - Salir                           ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnAno();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando Literalura...");
                    break;
                default:
                    System.out.println("Opción inválida. Elige entre 0 y 5.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String nombreLibro = teclado.nextLine();

        String url = URL_BASE + nombreLibro.replace(" ", "+");
        String json = consumoApi.obtenerDatos(url);
        DatosRespuesta respuesta = conversor.obtenerDatosRespuesta(json);

        if (respuesta.libros().isEmpty()) {
            System.out.println("No se encontró ningún libro con ese título.");
            return;
        }

        DatosLibro datosLibro = respuesta.libros().get(0);

        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("Este libro ya está registrado: " + libroExistente.get());
            return;
        }

        Libro libro = new Libro(datosLibro);

        if (!datosLibro.autores().isEmpty()) {
            DatosAutor datosAutor = datosLibro.autores().get(0);

            List<Autor> autoresExistentes = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre());
            Autor autor;

            if (!autoresExistentes.isEmpty()) {
                autor = autoresExistentes.get(0);
                System.out.println("Autor ya registrado: " + autor.getNombre());
            } else {
                autor = new Autor(datosAutor);
                autorRepository.save(autor);
                System.out.println("Nuevo autor guardado: " + autor.getNombre());
            }

            libro.setAutor(autor);
        }

        libroRepository.save(libro);
        System.out.println("Libro guardado exitosamente: " + libro);
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados aún.");
            return;
        }

        System.out.println("Libros registrados:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados aún.");
            return;
        }

        System.out.println("Autores registrados:");
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    private void listarAutoresVivosEnUnAno() {
        System.out.print("Ingrese el año que desea consultar: ");
        int ano = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autorRepository.autoresVivosEnElAno(ano);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + ano);
            return;
        }

        System.out.println("Autores vivos en " + ano + ":");
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Idiomas disponibles:");
        System.out.println("  es - Español");
        System.out.println("  en - Inglés");
        System.out.println("  fr - Francés");
        System.out.println("  pt - Portugués");
        System.out.print("Ingrese el código del idioma: ");
        String idioma = teclado.nextLine();

        List<Libro> libros = libroRepository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma.");
            return;
        }

        System.out.println("Libros en idioma '" + idioma + "':");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}
