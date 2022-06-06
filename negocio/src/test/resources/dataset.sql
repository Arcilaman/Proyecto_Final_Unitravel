insert into ciudad values(3, "Armenia");
insert into ciudad values(2, "Medellín");
insert into ciudad values(1, "Cartagena");

insert into administrador values ("2054", "admin@gmail.com", "Admin", "0000");

insert into administrador_hotel values ("1099", "admin1@gmail.com", "Admin1", "0001");
insert into administrador_hotel values ("1005", "admin2@gmail.com", "Admin2", "0002");

insert into usuario values ("1094", "pepe@gmail.com", "Pepito", "1234", 1);
insert into usuario values ("1095", "juan@gmail.com", "Juanito", "124", 2);
insert into usuario values ("1096", "ana@gmail.com", "Anita", "12", 3);

insert into usuario_telefonos values ("1094", "320315");
insert into usuario_telefonos values ("1095", "320315");
insert into usuario_telefonos values ("1096", "320780");
insert into usuario_telefonos values ("1094", "320780");


insert into cama values (1, "doble");
insert into cama values (2, "sencilla");
insert into cama values (3, "doble");

insert into hotel values(1, "caltagena menol calle 5 avenida nome olvides", "Los indios", 5, "7324051", "1099", 1);
insert into hotel values(2, "pruebalo bien en la 6ta de mil amores", "Los indios2.0", 4, "7324052", "1005", 3);
insert into hotel values(3, "Carrera five number three", "Los indios3.0", 5, "7324050", "1005", 1);


insert into habitacion values (1, 2, 196400, 1);
insert into habitacion values (2, 6, 540000, 1);
insert into habitacion values (3, 10, 1050000, 2);

insert into reserva values (1, 6, "activo", "2022-06-01", "2022-05-20", "2022-04-21", 889785,"1096" );
insert into reserva values (2, 6, "activo", "2022-07-01", "2022-06-20", "2022-05-21", 700000,"1094" );

insert into reserva_habitacion values (1,20000,1,2);
insert into reserva_habitacion values (2,10000,2,1);

insert into vuelo values (10, "vivaColombia", "activo", 1 , 2);


insert into silla values ("1", "10", 432232, 10);

insert into reserva_silla values (1,15000,1,"1");

insert into actividad values (1, "paseo en lancha", 158432, 1);
insert into actividad values (2, "pesca deportiva", 5000, 2);

insert into caracteristica values (0, "nn", 1,1);
insert into caracteristica values (2, "nn", 2,2);


insert into comentario values (1, 4, "Muy bueno", "2022-04-05", 1, "1094");

insert into vehiculo values ("bot670", 12235, "automovil", 1);