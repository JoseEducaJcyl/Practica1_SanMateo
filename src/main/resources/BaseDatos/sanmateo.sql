DROP DATABASE IF EXISTS Hospital;
CREATE DATABASE Hospital;  
USE Hospital; 

CREATE TABLE Paciente (
	IdPaciente int unsigned auto_increment primary key,
    DNI varchar(10),
    Nombre varchar(50),
    Direccion varchar(50),
    Telefono varchar(10),
    Email varchar(50),
    Password varchar(50)
    );

CREATE TABLE Citas (
	idCita int unsigned auto_increment primary key,
    nombreEspecialidad varchar(50),
    fecha date,
    idPaciente int unsigned not null, 
		constraint fk_paciente
			foreign key (IdPaciente)
            references Paciente(IdPaciente)
            ON DELETE CASCADE 
			ON UPDATE CASCADE
);

USE Hospital;

INSERT INTO Paciente (DNI, Nombre, Direccion, Telefono, Email, Password) VALUES
('12345678A', 'Ana García López', 'Calle Mayor 12, Madrid', '600111222', 'ana.garcia@email.com', 'ana1234'),
('87654321B', 'Carlos Ruiz Fernández', 'Avda. Libertad 45, Barcelona', '600333444', 'carlos.ruiz@email.com', 'carlos2024'),
('11223344C', 'María Sánchez Pérez', 'Calle Sol 8, Valencia', '600555666', 'maria.sanchez@email.com', 'mariaS!'),
('55667788D', 'Javier Torres Molina', 'Plaza España 3, Sevilla', '600777888', 'javier.torres@email.com', 'javi_88'),
('99887766E', 'Lucía Navarro Gil', 'Calle Luna 21, Bilbao', '600999000', 'lucia.navarro@email.com', 'luciaN#22'),
('44332211F', 'Pedro Jiménez Soto', 'Avda. Constitución 5, Zaragoza', '611222333', 'pedro.jimenez@email.com', 'pedro123'),
('66778899G', 'Elena Romero Vidal', 'Calle Río 14, Málaga', '611444555', 'elena.romero@email.com', 'elenaR!9'),
('22113344H', 'Diego Alonso Castro', 'Calle Mar 7, Alicante', '611666777', 'diego.alonso@email.com', 'diegoC_7'),
('88990011J', 'Sara Iglesias Prado', 'Avda. Andalucía 33, Granada', '611888999', 'sara.iglesias@email.com', 'saraI#2024'),
('33445566K', 'Miguel Ortega León', 'Calle Norte 19, Valladolid', '611000111', 'miguel.ortega@email.com', 'miguelO!');

INSERT INTO Citas (nombreEspecialidad, fecha, idPaciente) VALUES
('Cardiología', '2024-06-15', 1),
('Dermatología', '2024-06-18', 2),
('Traumatología', '2024-06-20', 3),
('Pediatría', '2024-06-22', 4),
('Oftalmología', '2024-06-25', 5),
('Neurología', '2024-06-28', 6),
('Ginecología', '2024-07-02', 7),
('Urología', '2024-07-05', 8),
('Endocrinología', '2024-07-08', 9),
('Medicina General', '2024-07-10', 10),
('Cardiología', '2024-07-12', 1),   
('Dermatología', '2024-07-15', 3),  
('Traumatología', '2024-07-18', 5); 

