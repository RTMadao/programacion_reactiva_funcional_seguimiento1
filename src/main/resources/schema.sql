CREATE TABLE IF NOT EXISTS plato (id INT NOT NULL AUTO_INCREMENT, nombre VARCHAR(100), descripcion VARCHAR(240), precio NUMERIC(8,2), PRIMARY KEY (id));
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Sopa', 'Deliciosa sopa de hueso', 7000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Mote de queso', 'Delicioso Mote de queso con arroz', 12000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Sancocho de costilla', 'Delicioso Sancocho de costilla con arroz', 15000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Seco', 'Delicioso Seco con proteina del dia, arroz y granos', 8000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Corriente', 'Deliciosa Corriente con proteina del dia, arroz, granos y sopa de hueso', 10000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Bandeja de carne asada', 'Deliciosa bandeja de carne asada con patacon', 20000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Arroz', 'Porcin de arroz adicional', 2000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Jugo Natural', 'Refrescante jugo Naranjo o Mandarina', 5000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Gaseosa', 'Refrescante Gaseosa Cocacola, cola roman', 3000);
INSERT INTO plato (nombre, descripcion, precio) VALUES ('Botella de agua', 'Refrescante Botella con agua', 3000);

CREATE TABLE IF NOT EXISTS cliente (id INT NOT NULL AUTO_INCREMENT, identificacion VARCHAR(30), nombre VARCHAR(100), direccion VARCHAR(240), telefono VARCHAR(20), PRIMARY KEY (id));
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486795', 'Carlos Paredes','cr 54 #25-30', '3125486795');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486796', 'Daniel Perez','cr 55 #25-31', '3125486796');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486797', 'Jose Hernandez','cr 34 #22-36', '3125486797');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486798', 'Luisa Salcedo','cr 35 #20-37', '3125486798');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486799', 'Daniela Ramos','cr 54 #20-33', '3125486799');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486794', 'Maria Florez','cr 53 #29-37', '3125486794');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486793', 'Isabel Alcala','cr 64 #26-38', '3125486793');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486792', 'Camilo Ruiz','cr 21 #2939', '3125486792');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486791', 'Andrea Hernandez','cr 84 #27-33', '3125486791');
INSERT INTO cliente (identificacion, nombre, direccion, telefono) VALUES ('TEL_3125486790', 'Carlos Acasia','cr 54 #24-30', '3125486790');

CREATE TABLE IF NOT EXISTS orden_local (id INT NOT NULL AUTO_INCREMENT, fecha TIMESTAMP, total NUMERIC(8,2), PRIMARY KEY (id));
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-27 12:00:00.00',15000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-27 12:30:00.00',20000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-27 13:20:00.00',22000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 12:00:00.00',7000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 12:20:00.00',40000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 13:10:00.00',5000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 13:20:00.00',16000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 13:40:00.00',12000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 14:20:00.00',50000);
INSERT INTO orden_local (fecha, total) VALUES ('2023-07-28 14:34:00.00',100000);

CREATE TABLE IF NOT EXISTS domicilio (id INT NOT NULL AUTO_INCREMENT, fecha TIMESTAMP, identificacion_cliente VARCHAR(30), valor_domicilio NUMERIC(8,2), total NUMERIC(8,2), PRIMARY KEY (id));
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-27 12:00:00.00','TEL_3125486799',5000,20000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-27 12:10:00.00','TEL_3125486795',4000,24000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-27 13:12:00.00','TEL_3125486793',3000,23000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-28 12:30:00.00','TEL_3125486793',3000,30000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-28 12:45:00.00','TEL_3125486795',4000,26000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-28 13:10:00.00','TEL_3125486799',5000,17000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-28 13:43:00.00','TEL_3125486791',5000,20000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-29 12:26:00.00','TEL_3125486795',4000,28000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-29 13:34:00.00','TEL_3125486799',5000,14000);
INSERT INTO domicilio (fecha, identificacion_cliente, valor_domicilio, total) VALUES ('2023-07-29 13:40:00.00','TEL_3125486791',5000,25000);

