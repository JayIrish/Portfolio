CREATE DATABASE HPSR2;

USE HPSR2;

CREATE TABLE HPSR2.`Directrice` (
  `UtilisateursID` INT(10) AUTO_INCREMENT,
  `UserName` VARCHAR(30) UNIQUE,
  `UserPassword` VARCHAR(30),
  `Telephone` VARCHAR(15),
  PRIMARY KEY (`UtilisateursID`)
);

CREATE TABLE HPSR2.`Immeuble` (
  `immeubleID` INT(10) NOT NULL AUTO_INCREMENT,
  `ImmeubleNom` VARCHAR(30) NOT NULL,
  `Adresse` VARCHAR(50) NOT NULL UNIQUE,
  `Ville` VARCHAR(30) NOT NULL,
  `Province` VARCHAR(30) NOT NULL,
  `Code Postal` VARCHAR(10),
  PRIMARY KEY (`immeubleID`)
);

CREATE TABLE HPSR2.`Porte` (
  `PorteID` INT(10) NOT NULL AUTO_INCREMENT,
  `Porte` INT(10) NOT NULL,
  `Etage` Int(10) NOT NULL,
  `Vacant` BOOLEAN NOT NULL,
  `ImmeubleID` INT(10) NOT NULL,
  PRIMARY KEY (`PorteID`),
  FOREIGN KEY (`ImmeubleID`) REFERENCES `Immeuble`(`immeubleID`)
);

CREATE TABLE HPSR2.`Locataire` (
  `LocataireID` INT(10) NOT NULL AUTO_INCREMENT,
  `LocataireNom` VARCHAR(30) NOT NULL,
  `LocatairePrenom` VARCHAR(30) NOT NULL,
  `Telephone` VARCHAR(15) UNIQUE,
  `PorteID` INT(10) NOT NULL,
  `Adresse` VARCHAR(150) NOT NULL UNIQUE,
  PRIMARY KEY (`LocataireID`),
  FOREIGN KEY (`PorteID`) REFERENCES `Porte`(`PorteID`)
);

CREATE TABLE HPSR2.`Preposer` (
  `PreposerID` INT(10) NOT NULL AUTO_INCREMENT,
  `ImmeubleID` INT(10) NOT NULL,
  `Telephone` VARCHAR(30) NOT NULL,
  `DebutQuart` VARCHAR(30) NOT NULL,
  `FinQuart` VARCHAR(30) NOT NULL,
  `RepasHeureDebut` VARCHAR(30),
  `PauseHeureDebut` VARCHAR(30),
  PRIMARY KEY (`PreposerID`),
  FOREIGN KEY (`ImmeubleID`) REFERENCES `Immeuble`(`immeubleID`)
);

CREATE TABLE HPSR2.`Service` (
  `ServiceID` INT(10) NOT NULL AUTO_INCREMENT,
  `ServiceNom` VARCHAR(30) NOT NULL UNIQUE,
  `NomJour` VARCHAR(20) NOT NULL,
  `LocataireID` INT(10) NOT NULL,
  `PreposerID` INT(10) NOT NULL,
  `HeureExecutionMin` VARCHAR(30),
  `HeureExecutionMax` VARCHAR(30),
  `HeureDebut` VARCHAR(30),
  `HeureFin` VARCHAR(30),
  `Duree` FLOAT,
  `Journalier` BOOLEAN,
  `Description` TEXT,
  PRIMARY KEY (`ServiceID`),
  FOREIGN KEY (`LocataireID`) REFERENCES `Locataire`(`LocataireID`),
  FOREIGN KEY (`PreposerID`) REFERENCES `Preposer`(`PreposerID`)
);

INSERT INTO `directrice` (`UserName`, `UserPassword`, `Telephone`) VALUES
('admin', 'admin', '514-555-5555');

INSERT INTO `immeuble`(`ImmeubleNom`, `Adresse`, `Ville`, `Province`, `Code Postal`) VALUES 
('HPSR1', '777 rue Free-Guy', 'Montreal', 'Quebec', 'H1H 1H1'),
('HPSR2', '9999 rue Victoire', 'Mascouche', 'Quebec', '2A2 2A2');


INSERT INTO `porte` (`Porte`, `Etage`, `Vacant`, `ImmeubleID`) VALUES
('101', '1', '0', '1'),
('102', '1', '0', '1'),
('103', '1', '0', '1'),
('104', '1', '0', '1'),
('105', '1', '0', '1'),
('106', '1', '0', '1'),
('107', '1', '0', '1'),
('108', '1', '0', '1'),
('109', '1', '0', '1'),
('110', '1', '0', '1'),
('201', '2', '0', '2'),
('202', '2', '0', '2'),
('203', '2', '0', '2'),
('204', '2', '0', '2'),
('205', '2', '0', '2'),
('206', '2', '0', '2'),
('207', '2', '0', '2'),
('208', '2', '0', '2'),
('209', '2', '0', '2'),
('210', '2', '0', '2');

INSERT INTO `locataire` (`LocataireNom`, `LocatairePrenom`, `Telephone`, `PorteID`, `Adresse`) VALUES
('Doe', 'John', '555-666-3621', '1', '777 rue Free-Guy #101, Montreal, QC, H1H 1H1'),
('Smith', 'Jane', '555-546-8543', '2', '777 rue Free-Guy #102, Montreal, QC, H1H 1H1'),
('Douglas', 'Jason', '555-685-943', '3', '777 rue Free-Guy #103, Montreal, QC, H1H 1H1'),
('Bima', 'Priya', '555-222-2345', '4', '777 rue Free-Guy #104, Montreal, QC, H1H 1H1'),
('Jackson', 'Dave', '555-666-1234', '5', '777 rue Free-Guy #105, Montreal, QC, H1H 1H1'),
('White', 'Walter', '555-876-1212', '6', '777 rue Free-Guy #106, Montreal, QC, H1H 1H1'),
('Pickman', 'Jesse', '555-666-4312', '7', '777 rue Free-Guy #107, Montreal, QC, H1H 1H1'),
('Potter', 'Harry', '555-777-7777', '8', '777 rue Free-Guy #108, Montreal, QC, H1H 1H1'),
('Hermione', 'Granger', '555-888-8888', '9', '777 rue Free-Guy #109, Montreal, QC, H1H 1H1'),
('Weasley', 'Ron', '555-999-9999', '10', '777 rue Free-Guy #110, Montreal, QC, H1H 1H1'),
('Rocky', 'Samuel', '555-123-4567', '11', '9999 rue Victoire #201, Mascouche, QC, 2A2 2A2'),
('Colt', 'Jeffrey', '555-876-3333', '12', '9999 rue Victoire #202, Mascouche, QC, 2A2 2A2'),
('Tum-tum', 'Michael', '555-666-2222', '13', '9999 rue Victoire #203, Mascouche, QC, 2A2 2A2'),
('Scott', 'Lucas', '555-555-5055', '14', '9999 rue Victoire #204, Mascouche, QC, 2A2 2A2'),
('Scott', 'Nathan', '555-999-5555', '15', '9999 rue Victoire #205, Mascouche, QC, 2A2 2A2'),
('Sawyer', 'Peyton', '555-111-1111', '16', '9999 rue Victoire #206, Mascouche, QC, 2A2 2A2'),
('James Scott', 'Haley', '555-222-2222', '17', '9999 rue Victoire #207, Mascouche, QC, 2A2 2A2'),
('Davis', 'Brooke', '555-333-3333', '18', '9999 rue Victoire #208, Mascouche, QC, 2A2 2A2'),
('Cooper', 'Sheldon', '555-444-4444', '19', '9999 rue Victoire #209, Mascouche, QC, 2A2 2A2'),
('Hofstader', 'Leonard', '555-555-5555', '20', '9999 rue Victoire #210,Mascouche, QC, 2A2 2A2');

INSERT INTO `preposer` (`ImmeubleID`, `Telephone`, `DebutQuart`, `FinQuart`, `RepasHeureDebut`, `PauseHeureDebut`) VALUES
('1', '450-555-8694', '07:15', '15:00', '13:15', '10:45'),
('1', '450-555-8694', '07:30', '15:30', '12:30', '09:30'),
('1', '450-555-8694', '08:00', '15:45', '13:15', '09:45'),
('1', '450-555-8694', '15:45', '23:15', '18:30', '21:00'),
('1', '450-555-8694', '16:15', '00:00', '18:45', '21:15'),
('1', '450-555-8694', '16:15', '00:00', '17:30', '21:30'),
('1', '450-555-8694', '10:00', '08:00', NULL, NULL),
('1', '450-555-8694', '00:00', '10:00', NULL, NULL),
('2', '450-555-8694', '07:15', '15:00', '13:15', '10:45'),
('2', '450-555-8694', '07:30', '15:30', '12:30', '09:30'),
('2', '450-555-8694', '08:00', '15:45', '13:15', '09:45'),
('2', '450-555-8694', '15:45', '23:15', '18:30', '21:00'),
('2', '450-555-8694', '16:15', '00:00', '18:45', '21:15'),
('2', '450-555-8694', '16:15', '00:00', '17:30', '21:30'),
('2', '450-555-8694', '10:00', '08:00', NULL, NULL),
('2', '450-555-8694', '00:00', '10:00', NULL, NULL);


INSERT INTO `Service`(`LocataireID`, `PreposerID`,  `ServiceNom`, `NomJour`, `HeureExecutionMin`, `HeureExecutionMax`, `Duree`) VALUES 
('1', '1', 'Lever', 'Lundi', '06:15', '08:15', '1.25'),
('1', '2','Coucher', 'Mardi', '20:15', '21:15', '1.50');