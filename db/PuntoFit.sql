CREATE DATABASE IF NOT EXISTS PuntoFit;
USE PuntoFit;


CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descrizione TEXT
);


CREATE TABLE Utente (
    id_utente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL, 
    ruolo VARCHAR(20) DEFAULT 'CLIENTE', 
    indirizzo_spedizione VARCHAR(255),
    telefono VARCHAR(20)
);


CREATE TABLE Prodotto (
    id_prodotto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descrizione TEXT,
    prezzo DECIMAL(10, 2) NOT NULL,
    quantita_disponibile INT NOT NULL DEFAULT 0, -- Per la gestione del magazzino
    immagine_url VARCHAR(255), -- Percorso della foto (es. "immagini/whey.jpg")
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria) ON DELETE SET NULL
);


CREATE TABLE Ordine (
    id_ordine INT AUTO_INCREMENT PRIMARY KEY,
    data_ordine TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    stato_ordine VARCHAR(30) DEFAULT 'IN_ATTESA', 
    totale DECIMAL(10, 2) NOT NULL,
    id_utente INT,
    FOREIGN KEY (id_utente) REFERENCES Utente(id_utente) ON DELETE CASCADE
);


CREATE TABLE Dettaglio_Ordine (
    id_dettaglio INT AUTO_INCREMENT PRIMARY KEY,
    id_ordine INT NOT NULL,
    id_prodotto INT,
    quantita INT NOT NULL,
    prezzo_unitario DECIMAL(10, 2) NOT NULL, 
    FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine) ON DELETE CASCADE,
    FOREIGN KEY (id_prodotto) REFERENCES Prodotto(id_prodotto) ON DELETE SET NULL
);

INSERT INTO Utente(nome,cognome,email,password_hash,ruolo)
VALUES('Daniele','Ferullo','danieleferullo10@gmail.com','123','Admin')
