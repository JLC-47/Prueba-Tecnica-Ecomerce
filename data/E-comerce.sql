create database Ecomerce;
use Ecomerce;

create table brand(
id int auto_increment primary key not null,
name varchar(50) not null
);

create table customers(
id int auto_increment primary key not null, 
name varchar(50) not null,
phone varchar(13) not null,
email varchar(60) not null
);

create table catalog(
id int auto_increment primary key not null,
name varchar(50) not null
);

create table products(
id int auto_increment primary key not null,
name varchar(50) not null,
description varchar(100) not null,
price decimal(10,2) not null,
stock int not null,
id_brand int not null,
id_catalog int not null,
foreign key (id_brand) references brand(id),
foreign key (id_catalog) references catalog(id)
);




create table favorites(
id int auto_increment primary key not null,
date timestamp default current_timestamp not null,

id_customer int not null,
id_product int not null,
foreign key (id_customer) references customers(id),
foreign key (id_product) references products(id)
);

create table f_history(
id int auto_increment primary key not null,
id_customer int not null,
id_product int not null,
action varchar(20) not null,
action_date timestamp default current_timestamp
);

-- inserciones a las tablas
insert into catalog (name) values 
('tecnología y cómputo'),
('audio y video'),
('papelería y útiles'),
('oficina y ergonomía'),
('accesorios b2c'),
('conectividad y redes'),
('almacenamiento digital'),
('impresión y escaneo');

insert into brand (name) values 
('carvajal tecnología'),
('norma'),
('samsung'),
('sony'),
('dell'),
('logitech'),
('hp'),
('lenovo'),
('bose'),
('kingston'),
('tp-link');

insert into customers (name, phone, email) values 
('juan pérez', '+573001234567', 'juan.perez@email.com'),
('maría lópez', '+573159876543', 'maria.lopez@email.com'),
('andrés mendoza', '+573105554422', 'andres.m@email.com'),
('carlos arturo', '+573204448899', 'carlos.a@email.com'),
('ana maría restrepo', '+573127776655', 'ana.restrepo@email.com'),
('diego gomez', '+573163332211', 'diego.g@email.com');

insert into products (name, description, price, stock, id_brand, id_catalog) values 
('portátil latitude 3420', 'procesador intel i5, 16gb ram, 512gb ssd', 2800000.00, 8, 5, 1),
('monitor gamer 24 pulgadas', 'pantalla fhd 144hz para diseño y juegos', 650000.00, 15, 3, 1),
('cuaderno kiut jean', 'cuaderno argollado 100 hojas cuadriculado', 15500.00, 120, 2, 3),
('resma de papel reprograf a4', 'caja de papel bond x 5 resmas de alta blancura', 85000.00, 45, 1, 3),
('audífonos wh-1000xm4', 'audífonos inalámbricos con cancelación de ruido', 990000.00, 0, 4, 2),
('mouse inalámbrico m185', 'mouse óptico compacto conexión 2.4 ghz', 45000.00, 30, 6, 5),
('teclado mecánico g413', 'teclado retroiluminado con switches táctiles', 320000.00, 12, 6, 1),
('impresora laserjet pro', 'impresora monocromática de alta velocidad', 890000.00, 4, 7, 8),
('silla ergonómica de oficina', 'silla con soporte lumbar y brazos ajustables', 480000.00, 25, 1, 4),
('lapicero kilométrico negro', 'caja de lapiceros negros x 12 unidades', 12000.00, 0, 2, 3),
('parlante bluetooth srs-xb13', 'parlante portátil resistente al agua', 190000.00, 18, 4, 2),
('disco duro externo 1tb', 'disco portátil usb 3.0 para copias de seguridad', 240000.00, 0, 3, 7),
('portátil thinkpad e14', 'procesador amd ryzen 5, 8gb ram, 256gb ssd', 2450000.00, 6, 8, 1),
('audífonos bose quietcomfort', 'audífonos premium con cancelación de ruido activa', 1200000.00, 5, 9, 2),
('memoria usb 64gb dt exodia', 'unidad flash usb 3.2 con anillo para llavero', 28000.00, 200, 10, 7),
('router rompemuros tl-wr940n', 'router inalámbrico de alta potencia 450mbps', 115000.00, 14, 11, 6),
('perforadora industrial de papel', 'perforadora de dos huecos capacidad 40 hojas', 55000.00, 0, 1, 4),
('organizador de escritorio', 'organizador metálico de malla con 3 compartimentos', 35000.00, 40, 1, 4),
('tarjeta micro sd 128gb canvas', 'tarjeta de memoria clase 10 con adaptador sd', 65000.00, 85, 10, 7),
('switch giga de 8 puertos', 'switch de escritorio para expansión de red cableada', 95000.00, 0, 11, 6);

insert into favorites (id_customer, id_product) values 
(1, 1),
(1, 5),
(1, 6),
(2, 2),
(2, 10),
(3, 3),
(3, 13),
(3, 17),
(4, 9),
(4, 12),
(5, 14),
(5, 15),
(5, 20);

insert into f_history (id_customer, id_product, action) values 
(1, 1, 'agregado'),
(1, 5, 'agregado'),
(1, 6, 'agregado'),
(2, 2, 'agregado'),
(2, 10, 'agregado'),
(3, 3, 'agregado'),
(3, 13, 'agregado'),
(3, 17, 'agregado'),
(4, 9, 'agregado'),
(4, 12, 'agregado'),
(5, 14, 'agregado'),
(5, 15, 'agregado'),
(5, 20, 'agregado'),
(1, 11, 'agregado'),
(1, 11, 'eliminado'),
(2, 15, 'agregado'),
(2, 15, 'eliminado'),
(4, 4, 'agregado'),
(4, 4, 'eliminado');



-- procedimiento almacenado para usar en el backend

delimiter //
create procedure pa_favorites(in id int)
begin 
	select b.name as marca, p.name from favorites f
    join products p on p.id = id_product
    join brand b on b.id = id_brand
    join customers c on c.id = id_customer where f.id_customer = id;
end //
delimiter ;

call pa_favorites(4);