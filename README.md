<div align="center">

# 📚 Literalura

### Aplicación para consultar y administrar libros y autores

<p>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.6-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 3.2.6">
  <img src="https://img.shields.io/badge/PostgreSQL-12+-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Proyecto-Educativo-6C63FF?style=for-the-badge" alt="Proyecto educativo">
</p>

<p>
  <strong>Challenge de Literalura — Alura + Oracle Next Education</strong>
</p>

</div>

<br>

## 📌 Índice

- [📖 ¿De qué trata?](#-de-qué-trata)
- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologías utilizadas](#️-tecnologías-utilizadas)
- [📂 Estructura del proyecto](#-estructura-del-proyecto)
- [⚙️ Requisitos previos](#️-requisitos-previos)
- [🚀 Instalación y configuración](#-instalación-y-configuración)
- [▶️ Ejecución](#️-ejecución)
- [🧭 Opciones del menú](#-opciones-del-menú)
- [🌐 Endpoint REST](#-endpoint-rest)
- [🧪 Pruebas](#-pruebas)
- [👤 Autor](#-autor)

## 📖 ¿De qué trata?

**Literalura** es una aplicación desarrollada en Java con Spring Boot para consultar libros mediante la API pública [Gutendex](https://gutendex.com/), registrar la información en PostgreSQL y realizar consultas sobre los libros y autores almacenados.

El proyecto fue creado como parte del challenge de **Alura y Oracle Next Education**. Su flujo principal se ejecuta desde una interfaz de consola, donde es posible buscar títulos, consultar autores, filtrar libros por idioma y visualizar los libros con mayor número de descargas.

Además del menú interactivo, el proyecto incluye un endpoint REST para consultar los libros registrados en la base de datos.

## ✨ Funcionalidades

- 🔎 Buscar libros por título utilizando la API de Gutendex.
- 💾 Guardar libros y autores en PostgreSQL.
- 🚫 Evitar registrar libros duplicados.
- 📚 Listar todos los libros almacenados.
- ✍️ Listar todos los autores registrados.
- 📅 Consultar autores vivos en un año determinado.
- 🌍 Filtrar libros por idioma.
- 👤 Buscar autores por nombre.
- 🏆 Consultar el top de libros registrados según sus descargas.
- 🌐 Consultar el top de libros de Gutendex.
- 🔗 Exponer los libros registrados mediante un endpoint REST.

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| **Java 17** | Lenguaje principal del proyecto |
| **Spring Boot 3.2.6** | Configuración y ejecución de la aplicación |
| **Spring Data JPA** | Persistencia y mapeo objeto-relacional |
| **Spring Web** | Endpoint REST para consultar libros |
| **Hibernate** | Implementación JPA |
| **PostgreSQL** | Base de datos relacional |
| **Jackson** | Conversión de respuestas JSON |
| **Gutendex API** | Consulta de libros y autores |
| **Maven** | Gestión de dependencias y ejecución |
| **JUnit / Spring Boot Test** | Pruebas automatizadas |

## 📂 Estructura del proyecto

```text
Literalura/
├── scriptsDB/
│   └── LiteraluraSQL.sql                 # Script de creación y consultas SQL
├── src/
│   ├── main/
│   │   ├── java/com/si3ri/literalura/
│   │   │   ├── controller/               # Controladores REST
│   │   │   ├── dto/                      # Objetos de transferencia de datos
│   │   │   ├── menu/                     # Menú interactivo de consola
│   │   │   ├── model/                    # Entidades y modelos
│   │   │   ├── repository/               # Repositorios JPA
│   │   │   ├── service/                  # Consumo de API y lógica de negocio
│   │   │   └── LiteraluraApplication.java
│   │   └── resources/
│   │       └── application.properties    # Configuración de PostgreSQL
│   └── test/                             # Pruebas del proyecto
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## ⚙️ Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

- [Java JDK 17](https://adoptium.net/)
- [PostgreSQL 12 o superior](https://www.postgresql.org/download/)
- [Git](https://git-scm.com/)

También necesitas una conexión a internet para utilizar las opciones que consultan la API de Gutendex.

## 🚀 Instalación y configuración

1. Clona el repositorio y entra en la carpeta del proyecto:

   ```bash
   git clone https://github.com/MarioSi3ri/Literalura.git
   cd Literalura
   ```

2. Crea la base de datos en PostgreSQL:

   ```sql
   CREATE DATABASE Literalura;
   ```

   El archivo [`scriptsDB/LiteraluraSQL.sql`](scriptsDB/LiteraluraSQL.sql) contiene el script de referencia para crear la base de datos, las tablas y realizar consultas de comprobación.

3. Configura las variables de entorno utilizadas por `application.properties`:

   En Linux o macOS:

   ```bash
   export DB_HOST=localhost
   export DB_USER=postgres
   export DB_PASSWORD=tu_contraseña
   ```

   En Windows PowerShell:

   ```powershell
   $env:DB_HOST="localhost"
   $env:DB_USER="postgres"
   $env:DB_PASSWORD="tu_contraseña"
   ```

   `DB_HOST` puede incluir el puerto si tu instalación no utiliza el predeterminado:

   ```bash
   export DB_HOST=localhost:5432
   ```

4. Verifica que PostgreSQL esté iniciado y ejecuta la aplicación.

## ▶️ Ejecución

### Linux o macOS

```bash
./mvnw spring-boot:run
```

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

También puedes compilar el proyecto y ejecutar el archivo generado:

```bash
./mvnw clean package
java -jar target/literalura-0.0.1-SNAPSHOT.jar
```

Al iniciar la aplicación se mostrará el menú interactivo en la terminal.

## 🧭 Opciones del menú

| Opción | Descripción | Internet |
|---:|---|:---:|
| **1** | Buscar un libro por título y guardarlo en la base de datos | Sí |
| **2** | Listar los libros registrados | No |
| **3** | Listar los autores registrados | No |
| **4** | Listar autores vivos en un año específico | No |
| **5** | Listar libros por idioma | No |
| **6** | Buscar un autor por nombre | No |
| **7** | Mostrar el top de libros registrados por descargas | No |
| **8** | Mostrar el top 10 de libros de Gutendex | Sí |
| **0** | Salir de la aplicación | No |

> 💡 Para registrar información, selecciona la opción **1**, escribe el título de un libro y confirma que la aplicación muestre el mensaje de guardado exitoso.

## 🌐 Endpoint REST

El proyecto incluye un controlador REST para consultar los libros registrados:

```http
GET http://localhost:8080/libros
```

Ejemplo con `curl`:

```bash
curl http://localhost:8080/libros
```

La respuesta contiene una lista JSON con el identificador, título, autor, idioma y número de descargas de cada libro.

## 🧪 Pruebas

Ejecuta las pruebas automatizadas con Maven:

```bash
./mvnw test
```

En Windows:

```powershell
.\mvnw.cmd test
```

## 👤 Autor

**Mario Sierra Neri**

- GitHub: [MarioSi3ri](https://github.com/MarioSi3ri)
- Repositorio: [Literalura](https://github.com/MarioSi3ri/Literalura)
- Copilot ayudó a mejorar el presente archivo README.

## 📄 Licencia

Este proyecto se distribuye bajo la **Licencia MIT**. Consulta el archivo [LICENSE](LICENSE) para conocer los términos completos.

---

<p align="center">
  Desarrollado como parte del programa <strong>Oracle Next Education + Alura</strong> 🚀
</p>
