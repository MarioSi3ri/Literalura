-- Creación de la base de datos: 'Literalura'.
CREATE DATABASE Literalura;

-- Consultas de las tablas.
SELECT DATABASE Literalura;

SELECT * FROM autores;

SELECT * FROM libros;

-- Crear la tabla de autores.
CREATE TABLE autores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nacimiento INT NOT NULL,
    deceso INT
);

-- Crear la tabla de libros.
CREATE TABLE libros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor_id INT REFERENCES autores(id) ON DELETE CASCADE,
    idioma VARCHAR(255),
    descargas DOUBLE PRECISION
);