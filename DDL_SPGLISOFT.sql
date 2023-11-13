DROP DATABASE IF EXISTS SPGLISOFT;

CREATE DATABASE SPGLISOFT DEFAULT CHARACTER SET utf8;
USE SPGLISOFT;

CREATE TABLE usuarios (
    id_usuario int not null auto_increment,
    nombre nvarchar(64) not null,
    apellido_paterno nvarchar(64) not null,
    apellido_materno nvarchar(64) not null,
    email nvarchar(30) not null,
    contrasena nvarchar(64) not null,
    matricula nvarchar(10),
    tipo_usuario enum('desarrollador', 'representante_proyecto', 'administrador') not null,
    PRIMARY KEY(id_usuario),
    UNIQUE (id_usuario),
    UNIQUE (matricula),
    UNIQUE (email)
);

CREATE TABLE participantes (
    id_participante int not null auto_increment,
    id_usuario int not null,
    nombre_proyecto nvarchar(64) not null,
    PRIMARY KEY (id_participante),
    UNIQUE (id_participante)
);

CREATE TABLE proyectos (
    nombre_proyecto nvarchar(64) not null,
    descripcion nvarchar(5000) not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    estado enum('por empezar', 'en curso', 'terminado'),
    id_usuario_responsable int not null,
    PRIMARY KEY(nombre_proyecto),
    UNIQUE (nombre_proyecto)
);

CREATE TABLE actividades (
    id_actividad int not null auto_increment,
    nombre_proyecto nvarchar(64) not null,
    id_desarrollador int null,
    titulo nvarchar(64) not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    estado enum('por empezar', 'en curso', 'terminado') default 'por empezar',
    esfuerzo_minutos int not null,
    descripcion nvarchar(1000) null,
    PRIMARY KEY(id_actividad),
    UNIQUE (id_actividad)
);

CREATE TABLE defectos (
    id_defecto int not null auto_increment,
    nombre_proyecto nvarchar(64) not null,
    titulo nvarchar(64) not null,
    descripcion nvarchar(5000) not null,
    fecha_reporte date not null,
    tipo enum('javascript', 'interfaz'),
    PRIMARY KEY(id_defecto),
    UNIQUE (id_defecto)
);

CREATE TABLE solicitudes_cambios (
    id_solicitud int not null auto_increment,
    nombre_proyecto nvarchar(64) not null,
    id_proponente int not null,
    titulo nvarchar(64) not null,
    descripcion nvarchar(64) not null,
    fechaSolicitud date not null,
    accion_propuesta nvarchar(1000) not null,
    razon_cambio nvarchar (300),
    impacto nvarchar(30),
    estado enum('aceptado', 'rechazado', 'no_asignado'),
    PRIMARY KEY(id_solicitud),
    UNIQUE (id_solicitud)
);
