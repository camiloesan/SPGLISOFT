DROP DATABASE IF EXISTS SPGLISOFT;
CREATE DATABASE SPGLISOFT DEFAULT CHARACTER SET utf8;
USE SPGLISOFT;

CREATE TABLE experiencia_educativa (
    id_experiencia_educativa INT NOT NULL AUTO_INCREMENT,
    experiencia_educativa VARCHAR(45),
    NRC INT NOT NULL,
    periodo VARCHAR(45),
    PRIMARY KEY (id_experiencia_educativa)
);

CREATE TABLE estado_proyecto (
    id_estado_proyecto INT NOT NULL AUTO_INCREMENT,
    estado_proyecto VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_estado_proyecto)
);

CREATE TABLE representante_proyecto (
    id_representante INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido_paterno VARCHAR(45) NOT NULL,
    apellido_materno VARCHAR(45) NOT NULL,
    numero_personal VARCHAR(45) NOT NULL,
    contrasena VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_representante)
);

CREATE TABLE desarrollador (
    id_desarrollador INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido_paterno VARCHAR(45) NOT NULL,
    apellido_materno VARCHAR(45) NOT NULL,
    matricula VARCHAR(45) NOT NULL,
    contrasena VARCHAR(45) NOT NULL,
    id_proyecto INT NULL,
    semestre INT NOT NULL,
    id_experiencia_educativa INT NOT NULL,
    PRIMARY KEY (id_desarrollador)
);

CREATE TABLE proyecto (
    id_proyecto INT NOT NULL AUTO_INCREMENT,
    nombre_proyecto VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    fecha_inicio VARCHAR(45) NOT NULL,
    fecha_fin VARCHAR(45) NOT NULL,
    estado_proyecto INT NOT NULL,
    id_representante INT NOT NULL,
    PRIMARY KEY (id_proyecto)
);

CREATE TABLE estado_solicitud (
    id_estado_solicitud INT NOT NULL AUTO_INCREMENT,
    estado_solicitud VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_estado_solicitud)
);

CREATE TABLE solicitud_cambio (
    id_solicitud INT NOT NULL AUTO_INCREMENT,
    id_proyecto INT NOT NULL,
    id_desarrollador INT NOT NULL,
    nombre_solicitud VARCHAR(50) NOT NULL,
    descripcion VARCHAR(300),
    fecha_solicitud DATE NOT NULL,
    accion_propuesta VARCHAR(300) NOT NULL,
    razon_cambio VARCHAR(300) NOT NULL,
    impacto_cambio VARCHAR(300) NOT NULL,
    id_estado_solicitud INT NOT NULL,
    fecha_revision DATE NULL,
    id_desarrollador_asignado INT NULL,
    id_representante INT NULL,
    PRIMARY KEY (id_solicitud)
);

CREATE TABLE estado_actividad (
    id_estado_actividad INT NOT NULL AUTO_INCREMENT,
    estado VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_estado_actividad)
);

CREATE TABLE actividad (
    id_actividad INT NOT NULL AUTO_INCREMENT,
    id_proyecto INT NOT NULL,
    id_desarrollador INT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    esfuerzo INT NOT NULL,
    id_estado INT NOT NULL,
    fecha_inicio VARCHAR(45) NOT NULL,
    fecha_fin VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_actividad)
);

CREATE TABLE tipo_defecto (
    id_tipo_defecto INT NOT NULL AUTO_INCREMENT,
    tipo_defecto VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_tipo_defecto)
);

CREATE TABLE estado_defecto (
    id_estado_defecto INT NOT NULL AUTO_INCREMENT,
    estado_defecto VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_estado_defecto)
);

CREATE TABLE defecto (
    id_defecto INT NOT NULL AUTO_INCREMENT,   
    id_proyecto INT NOT NULL,
    id_desarrollador INT NOT NULL,
    nombre_defecto VARCHAR(45) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    fecha_reporte DATE NOT NULL,
    id_tipo_defecto INT NOT NULL,
    id_estado_defecto INT NOT NULL,
    esfuerzo_estimado INT NOT NULL,
    PRIMARY KEY (id_defecto)
);

CREATE TABLE tipo_cambio (
    id_tipo_cambio INT NOT NULL AUTO_INCREMENT,
    tipo_cambio VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_tipo_cambio)
);

CREATE TABLE cambio (
    id_cambio INT NOT NULL AUTO_INCREMENT,
    id_proyecto INT NOT NULL,
    id_desarrollador INT NOT NULL,
    id_solicitud_cambio INT NOT NULL,
    id_tipo_cambio INT NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    PRIMARY KEY (id_cambio)
);

ALTER TABLE proyecto 
ADD CONSTRAINT fk_proyecto_estado_proyecto FOREIGN KEY(estado_proyecto) REFERENCES estado_proyecto(id_estado_proyecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_proyecto_representante_proyecto FOREIGN KEY(id_representante) REFERENCES representante_proyecto(id_representante) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE desarrollador 
ADD CONSTRAINT fk_desarrollador_proyecto FOREIGN KEY(id_proyecto) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_desarrollador_experiencia_educativa FOREIGN KEY(id_experiencia_educativa) REFERENCES experiencia_educativa(id_experiencia_educativa) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE solicitud_cambio 
ADD CONSTRAINT fk_solicitud_cambio_proyecto FOREIGN KEY(id_proyecto) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_solicitud_cambio_estado_solicitud FOREIGN KEY(id_estado_solicitud) REFERENCES estado_solicitud(id_estado_solicitud) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_solicitud_cambio_desarrollador FOREIGN KEY(id_desarrollador) REFERENCES desarrollador(id_desarrollador) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_solicitud_cambio_desarrollador_asignado FOREIGN KEY(id_desarrollador_asignado) REFERENCES desarrollador(id_desarrollador) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE actividad
ADD CONSTRAINT fk_actividad_desarrollador FOREIGN KEY(id_desarrollador) REFERENCES desarrollador(id_desarrollador) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_actividad_estado_actividad FOREIGN KEY(id_estado) REFERENCES estado_actividad(id_estado_actividad) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_actividad_proyecto FOREIGN KEY(id_proyecto) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE defecto 
ADD CONSTRAINT fk_defecto_desarrollador FOREIGN KEY(id_desarrollador) REFERENCES desarrollador(id_desarrollador) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_defecto_tipo_defecto FOREIGN KEY(id_tipo_defecto) REFERENCES tipo_defecto(id_tipo_defecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_defecto_proyecto FOREIGN KEY(id_proyecto) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_defecto_estado_defecto FOREIGN KEY(id_estado_defecto) REFERENCES estado_defecto(id_estado_defecto) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE cambio 
ADD CONSTRAINT fk_cambio_solicitud_cambio FOREIGN KEY(id_solicitud_cambio) REFERENCES solicitud_cambio(id_solicitud) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_cambio_desarrollador FOREIGN KEY(id_desarrollador) REFERENCES desarrollador(id_desarrollador) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_cambio_proyecto FOREIGN KEY(id_proyecto) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_cambio_tipo_cambio FOREIGN KEY(id_tipo_cambio) REFERENCES tipo_cambio(id_tipo_cambio) ON DELETE CASCADE ON UPDATE CASCADE;


insert into experiencia_educativa(experiencia_educativa, NRC, periodo)
    values ("Practicas para la ingenieria de software", 32451, "Agosto-Enero");
insert into experiencia_educativa(experiencia_educativa, NRC, periodo)
    values ("Servicio Social", 23442, "Febrero-Julio");

insert into estado_proyecto(estado_proyecto) values ("Por empezar");
insert into estado_proyecto(estado_proyecto) values ("En curso");
insert into estado_proyecto(estado_proyecto) values ("Finalizado");

insert into tipo_defecto(tipo_defecto) values ("Interfaz");
insert into tipo_defecto(tipo_defecto) values ("Base de datos");
insert into tipo_defecto(tipo_defecto) values ("JavaScript");

insert into tipo_cambio(tipo_cambio) values ("Interfaz");
insert into tipo_cambio(tipo_cambio) values ("Base de datos");
insert into tipo_cambio(tipo_cambio) values ("JavaScript");

insert into estado_solicitud(estado_solicitud) values ("Pendiente");
insert into estado_solicitud(estado_solicitud) values ("En proceso");
insert into estado_solicitud(estado_solicitud) values ("Concluida");

insert into estado_actividad(estado) values ("Asignada");
insert into estado_actividad(estado) values ("No asignada");
insert into estado_actividad(estado) values ("Concluida");

insert into estado_defecto(estado_defecto) values ("Propuesto");
insert into estado_defecto(estado_defecto) values ("No asignada");
insert into estado_defecto(estado_defecto) values ("Concluida");


insert into representante_proyecto(nombre, apellido_paterno, apellido_materno, numero_personal, contrasena)
    values ("Martin", "Cruz", "Carmona", "1500", "groyper");

insert into proyecto(nombre_proyecto, descripcion, fecha_inicio, fecha_fin, estado_proyecto, id_representante)
    values ("Accesibilidad en la computacion", "Proyecto para la mejora de sistemas de computo en el ambito de la accesibilidad","2023-01-01", "2023-12-12", 2, 1);

INSERT INTO desarrollador(nombre, apellido_paterno, apellido_materno, matricula, contrasena, id_proyecto, semestre, id_experiencia_educativa) VALUES
    ("Camilo", "Espejo", "Sanchez", "zs21013861", "groyper", 1, 5, 1),
    ("Francisco", "Lara", "Hernandez", "zs21013123", "12345", null, 5, 1),
    ("Roberto", "Gonzales", "Sanchez", "zs21013324", "12345", null, 7, 1),
    ("Antonio", "Guzman", "Barradas", "zs21013125", "12345", null, 7, 1),
    ("Maria", "Segura", "Hernandez", "zs21013888", "12345", null, 5, 1),
    ("Paulina", "Barraza", "Hernandez", "zs21013860", "12345", null, 5, 1),
    ("John", "Doe", "Smith", "zs21010001", "password123", 1, 3, 1),
    ("Alice", "Johnson", "Brown", "zs21020002", "securepass", 1, 6, 1),
    ("Bob", "Anderson", "White", "zs21030003", "pass1234", 1, 4, 1);


insert into actividad(id_proyecto, id_desarrollador, nombre, descripcion, esfuerzo, id_estado, fecha_inicio, fecha_fin) values 
    (1, null, "actividad inicial", "Modulo de informacion", 140, 2, "2023-01-06", "2023-01-30"),
    (1, null, "implementacion de notificaciones", "notificar al usuario de fallas o novedades del sistema", 120, 2, "2023-01-06", "2023-01-30"),
    (1, null, "modulos de actualizacion", "proveer una interfaz para la actualizacion de la informacion", 240, 2, "2023-01-06", "2023-01-30"),
    (1, null, "añadir soporte a archivos de audio", "soportar archivos de audio en los formatos mas utilizados(mp3,wav,flac)", 300, 2, "2023-01-06", "2023-01-30"),
    (1, null, "mejorar interfaz de usuario", "realizar mejoras en la interfaz de usuario para una experiencia más amigable", 200, 2, "2023-02-01", "2023-02-15"),
    (1, null, "optimizar rendimiento del sistema", "realizar ajustes para mejorar el rendimiento del sistema", 150, 2, "2023-02-16", "2023-03-05"),
    (1, null, "implementar funcionalidad de búsqueda", "agregar una funcionalidad de búsqueda para facilitar la navegación", 180, 2, "2023-03-06", "2023-03-20"),
    (1, null, "realizar pruebas de seguridad", "realizar pruebas exhaustivas de seguridad en el sistema", 250, 2, "2023-03-21", "2023-04-10"),
    (1, null, "documentar código", "crear documentación detallada para el código del proyecto", 120, 2, "2023-04-11", "2023-04-25");

insert into solicitud_cambio(id_proyecto, id_desarrollador, nombre_solicitud, descripcion, fecha_solicitud, accion_propuesta, razon_cambio, impacto_cambio, id_estado_solicitud, fecha_revision, id_desarrollador_asignado) values 
    (1, 1, 'mejora de imagen(nuevo)', 'mejorar la apariencia de las imagenes y como son mostradas al actualizar la ventana', now(), 'mejora estetica', 'diseño no intuitivo para usuarios inexpertos', 'todos los usuarios', 1, null, null);

insert into cambio(id_proyecto, id_desarrollador, id_solicitud_cambio, id_tipo_cambio, nombre, descripcion) values 
    (1, 1, 1, 1, 'cambio implementado al 50%', 'contiene lo minimo requerido para poder funcionar'),
    (1, 1, 1, 2, 'cambio terminado', 'se completo la funcionalidad, afectando minimamente a otros modulos del sistema'),
    (1, 1, 1, 1, 'actualización de interfaz de usuario', 'se realizaron cambios en la apariencia para mejorar la experiencia del usuario'),
    (1, 1, 1, 2, 'añadir funcionalidad de búsqueda', 'se implementó una barra de búsqueda para facilitar la navegación'),
    (1, 1, 1, 2, 'optimización de rendimiento', 'se realizaron ajustes para mejorar el rendimiento del sistema'),
    (1, 2, 1, 1, 'nuevas características agregadas', 'se añadieron varias funcionalidades nuevas al sistema'),
    (1, 2, 1, 2, 'correcciones de seguridad', 'se realizaron correcciones para mejorar la seguridad del sistema'),
    (1, 2, 1, 2, 'documentación actualizada', 'se actualizó la documentación del código para reflejar los cambios realizados');

INSERT INTO defecto(id_proyecto, id_desarrollador, nombre_defecto, descripcion, fecha_reporte, id_tipo_defecto, id_estado_defecto, esfuerzo_estimado) VALUES
    (1, 1, 'Inicializacion de elemento', 'Fallo al mostrar las imágenes correctamente al abrir una nueva ventana', NOW(), 1, 1, 240),
    (1, 1, 'Problema de carga lenta', 'La aplicación tiene problemas de rendimiento al cargar grandes conjuntos de datos', NOW(), 1, 1, 180),
    (1, 1, 'Error de cálculo', 'El sistema presenta errores en los cálculos de impuestos en ciertas circunstancias', NOW(), 2, 1, 120),
    (1, 1, 'Interfaz no responsiva', 'La interfaz de usuario no responde correctamente en dispositivos móviles', NOW(), 3, 1, 150),
    (1, 1, 'Problema de seguridad', 'Se ha identificado un problema de seguridad en la autenticación del sistema', NOW(), 1, 1, 200),
    (1, 1, 'Fallo en la validación de formularios', 'El sistema permite enviar formularios incompletos sin validar correctamente', NOW(), 2, 1, 160),
    (1, 1, 'Error en la generación de informes', 'Los informes generados no muestran la información correcta en ciertos casos', NOW(), 2, 1, 220),
    (1, 1, 'Problema de accesibilidad', 'El sistema no cumple con los estándares de accesibilidad para usuarios con discapacidades', NOW(), 3, 1, 180);
