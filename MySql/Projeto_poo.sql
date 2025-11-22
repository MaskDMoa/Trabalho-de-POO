CREATE DATABASE projeto_poo;
USE projeto_poo;

CREATE TABLE usuario(
    id INT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    isVendedor BOOLEAN NOT NULL DEFAULT false,
    PRIMARY KEY(id)
);

CREATE TABLE loja (
    idLoja INT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario(id),
    
    PRIMARY KEY(idLoja)
);

CREATE TABLE itens (
    idItem INT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200),
    quantidade INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    idLoja INT NOT NULL,
    
    PRIMARY KEY(idItem),
    
    FOREIGN KEY (idLoja) REFERENCES loja(idLoja)
        ON DELETE CASCADE 
);
