INSERT INTO estudiantes(codigo, nombre, apellido, email, create_at ) VALUES('100', 'Andres', 'Perez', 'aperez@gmail.com', NOW());
INSERT INTO estudiantes(codigo, nombre, apellido, email, create_at ) VALUES('200', 'Rocio', 'Angel', 'rangel@gmail.com', NOW());
INSERT INTO estudiantes(codigo, nombre, apellido, email, create_at ) VALUES('300', 'Jorge', 'Quintero', 'jquintero@gmail.com', NOW());
INSERT INTO estudiantes(codigo, nombre, apellido, email, create_at ) VALUES('400', 'Ana', 'Lozano', 'alozano@gmail.com', NOW());
INSERT INTO estudiantes(codigo, nombre, apellido, email, create_at ) VALUES('500', 'Pedro', 'Contreras', 'pcontreras@gmail.com', NOW());

INSERT INTO asignaturas(codigo, nombre, create_at ) VALUES('1', 'Programacion Avanzada', NOW());
INSERT INTO asignaturas(codigo, nombre, create_at ) VALUES('2', 'Arquitectura', NOW());
INSERT INTO asignaturas(codigo, nombre, create_at ) VALUES('3', 'Estadistica', NOW());
INSERT INTO asignaturas(codigo, nombre, create_at ) VALUES('4', 'Calculo Integral', NOW());

INSERT INTO grupos(codigo, nombre, create_at, asignatura_id ) VALUES('100', 'Grupo 1', NOW(),1);
INSERT INTO grupos(codigo, nombre, create_at, asignatura_id ) VALUES('200', 'Grupo 2', NOW(),1);
INSERT INTO grupos(codigo, nombre, create_at, asignatura_id ) VALUES('300', 'Grupo 3', NOW(),2);
INSERT INTO grupos(codigo, nombre, create_at, asignatura_id ) VALUES('400', 'Grupo 4', NOW(),2);
