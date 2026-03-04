# 📚 Literalura

Aplicación de consola desarrollada con **Java**, **Spring Boot** y **PostgreSQL** que permite buscar libros usando la API de Gutendex y guardarlos en una base de datos local.

---

## 📋 Funcionalidades

- 🔍 Buscar libro por título (consume la API de Gutendex)
- 📖 Listar todos los libros registrados en la base de datos
- 👤 Listar todos los autores registrados
- 🗓️ Listar autores vivos en un año determinado
- 🌍 Listar libros por idioma

---

## 🛠️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** — para conectar con la base de datos
- **PostgreSQL** — base de datos relacional
- **Jackson** — para convertir JSON en objetos Java
- **Gutendex API** — API gratuita de libros (no requiere API Key)

---

## 📁 Estructura del proyecto

```
src
└── main
    ├── java
    │   └── com.aluracursos.literalura
    │       ├── model
    │       │   ├── DatosAutor.java       → recibe JSON del autor
    │       │   ├── DatosLibro.java       → recibe JSON del libro
    │       │   ├── DatosRespuesta.java   → recibe JSON completo de la API
    │       │   ├── Autor.java            → entidad, tabla autores en BD
    │       │   └── Libro.java            → entidad, tabla libros en BD
    │       ├── service
    │       │   ├── IConvierteDatos.java  → interfaz del conversor
    │       │   ├── ConvierteDatos.java   → convierte JSON en objetos Java
    │       │   └── ConsumoAPI.java       → hace peticiones HTTP
    │       ├── repository
    │       │   ├── AutorRepository.java  → acceso a la tabla autores
    │       │   └── LibroRepository.java  → acceso a la tabla libros
    │       ├── principal
    │       │   └── Principal.java        → menú y lógica principal
    │       └── LiteraluraApplication.java → punto de entrada
    └── resources
        └── application.properties        → configuración de la BD
```

---

## ⚙️ Configuración antes de correr el proyecto

### 1. Requisitos previos

- Java 17 instalado
- PostgreSQL instalado y corriendo
- IntelliJ IDEA (recomendado)

### 2. Crear la base de datos en PostgreSQL

Abre PostgreSQL y ejecuta:

```sql
CREATE DATABASE literalura;
```

### 3. Configurar las variables de entorno en IntelliJ

Ve a `Run → Edit Configurations → Environment Variables` y agrega:

```
DB_HOST=localhost
DB_NAME=literalura
DB_USER=postgres
DB_PASSWORD=tu_contraseña
```

### 4. Correr el proyecto

Haz click en el botón **Run ▶️** en IntelliJ sobre la clase `LiteraluraApplication.java`.

Spring Boot creará las tablas `autores` y `libros` automáticamente en PostgreSQL.

---

## 🗄️ Tablas en la base de datos

### Tabla `autores`
| Campo          | Tipo    | Descripción                        |
|----------------|---------|------------------------------------|
| id             | Long    | Identificador único (autoincremental) |
| nombre         | String  | Nombre completo del autor          |
| anoNacimiento  | Integer | Año de nacimiento (puede ser null) |
| anoMuerte      | Integer | Año de muerte (null si sigue vivo) |

### Tabla `libros`
| Campo     | Tipo    | Descripción                           |
|-----------|---------|---------------------------------------|
| id        | Long    | Identificador único (autoincremental) |
| titulo    | String  | Título del libro (único)              |
| idioma    | String  | Código del idioma (es, en, fr, pt)    |
| descargas | Integer | Número de descargas en Gutendex       |
| autor_id  | Long    | Referencia al autor del libro (FK)    |

---

## 🌐 API utilizada

**Gutendex** — `https://gutendex.com`

- No requiere registro ni API Key
- Acceso gratuito y abierto
- Contiene más de 70.000 libros del Proyecto Gutenberg

Ejemplo de consulta:
```
https://gutendex.com/books/?search=don+quijote
```

---

## 📖 Cómo usar la aplicación

Al correr el proyecto verás este menú en la consola:

```
╔══════════════════════════════════════╗
║          LITERALURA MENÚ             ║
╠══════════════════════════════════════╣
║  1 - Buscar libro por título         ║
║  2 - Listar libros registrados       ║
║  3 - Listar autores registrados      ║
║  4 - Listar autores vivos en un año  ║
║  5 - Listar libros por idioma        ║
║  0 - Salir                           ║
╚══════════════════════════════════════╝
```

### Opción 1 — Buscar libro por título
Escribe el nombre del libro. La app lo busca en Gutendex, guarda el libro y su autor en la base de datos. Si el libro ya está registrado, te avisa sin duplicarlo.

### Opción 2 — Listar libros registrados
Muestra todos los libros que has buscado y guardado hasta el momento.

### Opción 3 — Listar autores registrados
Muestra todos los autores de los libros que has guardado.

### Opción 4 — Listar autores vivos en un año
Ingresa un año (por ejemplo 1600) y la app muestra todos los autores que estaban vivos en ese año.

### Opción 5 — Listar libros por idioma
Ingresa el código del idioma y la app muestra todos los libros en ese idioma:
- `es` → Español
- `en` → Inglés
- `fr` → Francés
- `pt` → Portugués

---

## 👨‍💻 Autor

Proyecto desarrollado como parte del curso de **Java y Spring Boot** de Alura Latam.
