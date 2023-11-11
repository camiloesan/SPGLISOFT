USE SPGLISOFT;

ALTER TABLE participantes 
ADD CONSTRAINT FK_Participantes_Usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_Participantes_Proyectos FOREIGN KEY(nombre_proyecto) REFERENCES proyectos(nombre_proyecto);

ALTER TABLE proyectos 
ADD CONSTRAINT FK_Proyectos_Usuarios FOREIGN KEY(id_usuario_responsable) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE actividades
ADD CONSTRAINT FK_Actividades_Usuarios FOREIGN KEY(id_desarrollador) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_Actividades_Proyectos FOREIGN KEY(nombre_proyecto) REFERENCES proyectos(nombre_proyecto) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE solicitudes_cambios
ADD CONSTRAINT FK_SolicitudesCambios_Proyectos FOREIGN KEY(nombre_proyecto) REFERENCES proyectos(nombre_proyecto) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_SolicitudesCambios_Usuarios FOREIGN KEY(id_proponente) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE defectos
ADD CONSTRAINT FK_Defectos_Proyectos FOREIGN KEY(nombre_proyecto) REFERENCES proyectos(nombre_proyecto) ON DELETE CASCADE ON UPDATE CASCADE;
