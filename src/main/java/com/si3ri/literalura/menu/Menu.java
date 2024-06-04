package com.si3ri.literalura.menu;

import com.si3ri.literalura.model.Autores;
import com.si3ri.literalura.model.DatosAutores;
import com.si3ri.literalura.model.DatosLibros;
import com.si3ri.literalura.model.Libros;
import com.si3ri.literalura.repository.AutoresRepository;
import com.si3ri.literalura.repository.LibrosRepository;
import com.si3ri.literalura.service.ConvertirDatos;
import com.si3ri.literalura.service.ConvertirDatosAutores;
import com.si3ri.literalura.service.ConsumoAPI;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    private final ConsumoAPI consumoAPI = new ConsumoAPI(); // Objeto utilizado para consumir y convertir datos de la API.
    private final ConvertirDatos convertir = new ConvertirDatos();
    private final ConvertirDatosAutores convertirAutor = new ConvertirDatosAutores();
    private final LibrosRepository repositorio1; // Repositorio para almacenar y recuperar libros.
    private final AutoresRepository repositorio2;
    private final Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/"; // URL base para acceder a la API.
    private List<Libros> libros;
    private List<Autores> autores;
    private Optional<Autores> buscarAutores;

    // Presenta un menú al usuario y se ejecuta dependiendo de la opción seleccionada.
    public void Menu() {
        var numeroOpcion = -1;
        while (numeroOpcion != 0) {
            var menu = """
                    
                    ===¡BIENVENIDO A LA CONSULTA DE UN LIBRO!===
                    
                    * Seleccione un número para iniciar la opción deseada:
                    --------------------------------------------------------
                    1 - Buscar libro por el título.
                    2 - Listar los libros registrados en la BD.
                    3 - Listar los autores registrados en la BD.
                    4 - Listar los autores vivos en un determinado año en la BD.
                    5 - Listar los libros por idioma en la BD.
                    6 - Buscar autores por el nombre en la BD.
                    7 - Top 7 libros en la BD.
                    8 - Top 10 libros en la API (WiFi requerido).
                    0 - Salir.
                    --------------------------------------------------------
                    """;
            System.out.println(menu);
            try {
            numeroOpcion = teclado.nextInt();
            teclado.nextLine();
            switch (numeroOpcion) {
                case 1:
                    buscarLibroPorElTitulo();
                    break;
                case 2:
                    mostrarLibrosListados();
                    break;
                case 3:
                    mostrarAutoresListados();
                    break;
                case 4:
                    mostrarAutoresVivosEnDeterminadoAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    buscarAutoresPorElNombre();
                    break;
                case 7:
                    top5LibrosDB();
                    break;
                case 8:
                    top10LibrosAPI();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija un número del 1 al 8.");
            }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número del 1 al 8.");
                teclado.nextLine();
            }
        }
        teclado.close();
        System.exit(0);
    }

    // Constructor que inicializa los repositorios de libros y autores.
    public Menu(LibrosRepository repo1, AutoresRepository repo2) {
        this.repositorio1 = repo1;
        this.repositorio2 = repo2;
    }

    //  Obtiene y muestra los datos del libro de la API.
    private DatosLibros getDatosLibro(String tituloLibro) {
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search="+ tituloLibro.replace(" ", "+"));
        DatosLibros datos = convertir.obtenerDatos(json, DatosLibros.class);
        return datos;
    }
    private DatosAutores getDatosAutor(String tituloLibro) {
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search="+ tituloLibro.replace(" ", "+"));
        DatosAutores datos = convertirAutor.obtenerDatos(json, DatosAutores.class);
        return datos;
    }

    // Texto que indica la acción a realizar.
    private String seleccion() {
        System.out.println("Escriba el nombre del libro a buscar: ");
        var tituloLibro = teclado.nextLine();
        return tituloLibro;
    }

    // Permite buscar un libro por su título, consultando la API y guardando los datos si el libro no está en la base de datos.
    public void buscarLibroPorElTitulo() {
        mostrarLibrosListados();
        String libroBuscado = seleccion();

        libros = libros != null ? libros : new ArrayList<>();

        Optional<Libros> nombreDelLibro = libros.stream()
                .filter(l -> l.getTitulo().toLowerCase()
                        .contains(libroBuscado.toLowerCase()))
                .findFirst();

        if(nombreDelLibro.isPresent()) {
            var libroEncontrado = nombreDelLibro.get();
            System.out.println(libroEncontrado);
            System.out.println("""
                    
                    - El libro ya esta listado, pruebe con otro.""");
        } else {
            try {
                DatosLibros datosLibro = getDatosLibro(libroBuscado);
                System.out.println(datosLibro);

                if (datosLibro != null) {
                    Optional<Libros> libroEnRepo = repositorio1.findAll().stream() // Verificar en el repositorio si el libro ya existe.
                            .filter(l -> l.getTitulo().toLowerCase().contains(datosLibro.titulo().toLowerCase()))
                            .findFirst();

                    if (libroEnRepo.isPresent()) {
                        System.out.println("""
                                
                                - El libro ya existe en la base de datos, pruebe con otro.""");
                    } else {
                        DatosAutores datosAutor = getDatosAutor(libroBuscado);
                        if (datosAutor != null) {
                            List<Autores> autores = repositorio2.findAll();
                            autores = autores != null ? autores : new ArrayList<>();

                            Optional<Autores> nombreDelAutor = autores.stream()
                                    .filter(a -> datosAutor.nombre() != null &&
                                            a.getNombre().toLowerCase().contains(datosAutor.nombre().toLowerCase()))
                                    .findFirst();

                            Autores autor;
                            if (nombreDelAutor.isPresent()) {
                                autor = nombreDelAutor.get();
                            } else {
                                autor = new Autores(
                                        datosAutor.nombre(),
                                        datosAutor.nacimiento(),
                                        datosAutor.deceso()
                                );
                                repositorio2.save(autor);
                            }

                            Libros libro = new Libros(
                                    datosLibro.titulo(),
                                    autor,
                                    datosLibro.idioma() != null ? datosLibro.idioma() : Collections.emptyList(),
                                    datosLibro.descargas()
                            );

                            libros.add(libro);
                            autor.setLibros(libros);

                            System.out.println(libro);
                            repositorio1.save(libro);

                        System.out.println("""
                    
                       - El libro se guardo exitosamente.""");
                        } else {
                            System.out.println("""
                        
                        - No se encontró el autor para el libro.""");
                        }
                    }
                } else {
                    System.out.println("""
                
                - No se encontró el libro.""");
                }
            } catch (Exception e) {
                System.out.println("- Excepción: " + e.getMessage());
            }
        }

    }

    // Muestra una lista de libros guardados en la base de datos.
    private void mostrarLibrosListados(){
        try{
            List<Libros> libros = repositorio1.findAll();
            libros.stream()
                    .sorted(Comparator.comparing(Libros::getDescargas))
                    .forEach(System.out::println);
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            libros = new ArrayList<>();
        }
    }

    // Muestra una lista de autores guardados en la base de datos.
    public void mostrarAutoresListados(){
        autores = repositorio2.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    // Muestra los autores vivos dependiendo el año.
    public void mostrarAutoresVivosEnDeterminadoAnio(){
        System.out.println("Ingrese el año: ");
        int anio = teclado.nextInt();
        autores = repositorio2.findAll();
        List<String> autoresNombre = autores.stream()
                .filter(a-> (a.getDeceso() >= anio) && (a.getNacimiento() <= anio))
                .map(a->a.getNombre())
                .collect(Collectors.toList());
        System.out.println("""
        
        * Autores vivos en un determinado año: *""");
        if (autoresNombre.isEmpty()) {
            System.out.println("No se encontraron autores para el año " + anio);
        } else {
            autoresNombre.forEach(System.out::println);
        }
    }

    // Muestra los libros dependiendo del idioma seleccionado.
    public void mostrarLibrosPorIdioma(){
        libros = repositorio1.findAll();
        List<String> idiomasDeLibros = libros.stream()
                .map(Libros::getIdioma)
                .distinct()
                .collect(Collectors.toList());
        idiomasDeLibros.forEach(idioma -> {
            switch (idioma){
                case "en":
                    System.out.println("en - English");
                    break;
                case "es":
                    System.out.println("es - Español");
                    break;
                case "pt":
                    System.out.println("pt - Portugués");
                    break;
                case "fr":
                    System.out.println("fr - Frances");
                    break;
            }
        });
        System.out.println(" ");
        System.out.println("Ingrese las siglas del idioma en que busca los libros: ");
        String idiomaBuscado = teclado.nextLine();
        List<Libros> librosBuscados = libros.stream()
                .filter(l->l.getIdioma().contains(idiomaBuscado))
                .collect(Collectors.toList());
        if (librosBuscados.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idiomaBuscado);
        } else {
            librosBuscados.forEach(System.out::println);
        }
    }

    // Muestra los datos del autor buscado.
    public void buscarAutoresPorElNombre(){
        System.out.println("Ingrese el nombre del autor a buscar: ");
        var nombreAutor = teclado.nextLine();
        buscarAutores = repositorio2.findByNombreContainingIgnoreCase(nombreAutor);
        if(buscarAutores.isPresent()){
            System.out.println(buscarAutores.get());
        }else{
            System.out.println("""
        
        - Autor no encontrado""");
        }
    }

    // Muestra una lista ordenada de los mejores libros dependiendo de los que estén guardados en la base de datos.
    public void top5LibrosDB(){
        try {
            List<Libros> libros = repositorio1.findAll();
            List<Libros> librosEnOrden = libros.stream()
                    .sorted(Comparator.comparingDouble(Libros::getDescargas).reversed())
                    .collect(Collectors.toList());
            List<Libros> top5Libros = librosEnOrden.subList(0, Math.min(7, librosEnOrden.size()));

            for (int i = 0; i < top5Libros.size(); i++) {
                System.out.println((i + 1) + ". " + top5Libros.get(i));
            }

        } catch(NullPointerException e){
            System.out.println(e.getMessage());
            libros = new ArrayList<>();
        }
    }

    // Muestra los 10 mejores libros de acuerdo a la API.
    public void top10LibrosAPI() {
        try {
            String json = consumoAPI.obtenerDatos(URL_BASE + "?sort");

            List<DatosLibros> datosLibros = convertir.obtenerDatosArray(json, DatosLibros.class);
            List<DatosAutores> datosAutor = convertirAutor.obtenerDatosArray(json, DatosAutores.class);

            List<Libros> libros = new ArrayList<>();
            for (int i = 0; i < datosLibros.size(); i++) {
                Autores autor = new Autores(
                        datosAutor.get(i).nombre(),
                        datosAutor.get(i).nacimiento(),
                        datosAutor.get(i).deceso());

                Libros libro = new Libros(
                        datosLibros.get(i).titulo(),
                        autor,
                        datosLibros.get(i).idioma(),
                        datosLibros.get(i).descargas());
                libros.add(libro);
            }

            libros.sort(Comparator.comparingDouble(Libros::getDescargas).reversed());

            List<Libros> top10Libros = libros.subList(0, Math.min(10, libros.size()));

            for (int i = 0; i < top10Libros.size(); i++) {
                System.out.println((i + 1) + ". " + top10Libros.get(i));
            }

        } catch (NullPointerException e) {
            System.out.println("- Error: " + e.getMessage());
        }
    }
}