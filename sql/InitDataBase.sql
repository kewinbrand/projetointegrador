--CRIA O BANCO DE DADOS
USE master
GO

IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'PROJETO_INTEGRADOR')
	CREATE DATABASE PROJETO_INTEGRADOR
GO

USE PROJETO_INTEGRADOR
GO

--CRIA TODAS AS TABELAS
IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'PRODUTO')
BEGIN
	CREATE TABLE PRODUTO(
	  Produto varchar(20) not null,
	  NomeCompleto varchar(100),
	  QtdMiniEstoque int,
	  QtdLoteComprar int,
  
	  CONSTRAINT PK_PRODUTO PRIMARY KEY (Produto)
	)
END
GO

IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'VENDA')
BEGIN
	CREATE TABLE VENDA(
	  Produto varchar(20),
	  Quantidade int,
	  ValorUn numeric(8,2),
	  Desconto numeric(8,2),
	  AliquotaICMS numeric(8,2),
	  Obs varchar(40)  
  
	  CONSTRAINT FK_VENDA_PRODUTO FOREIGN KEY (Produto) REFERENCES Produto(Produto)
	)
END
GO


IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = 'MOVIMENTOESTOQUE')
BEGIN
	CREATE TABLE MOVIMENTOESTOQUE(
	  IdMovimento int not null identity(1,1),
	  DataHora datetime DEFAULT GETDATE(),
	  Produto varchar(20),
	  Quantidade int  
  
	  CONSTRAINT FK_MOVIMENTOESTOQUE_PRODUTO FOREIGN KEY (Produto) REFERENCES Produto(Produto)  
	)
END
GO

--CRIA TODAS AS TRIGGER's
IF NOT EXISTS(SELECT * FROM sys.triggers WHERE name = 'trMovimentaEstoque')
BEGIN
	EXEC('
	CREATE TRIGGER trMovimentaEstoque
	ON [VENDA]
	AFTER INSERT
	AS
	BEGIN
		DECLARE @Produto varchar(20), @Quantidade int
	
		SELECT @Produto = Produto, @Quantidade = Quantidade FROM INSERTED
	
		INSERT INTO MOVIMENTOESTOQUE(Produto, Quantidade)
		VALUES (@Produto, @Quantidade)
	
	END
	')
END
GO

IF NOT EXISTS(SELECT * FROM sys.triggers WHERE name = 'trVerificaNecessidadeReposicao')
BEGIN
	EXEC('
		CREATE TRIGGER trVerificaNecessidadeReposicao
		ON [MOVIMENTOESTOQUE]
		AFTER INSERT
		AS
		BEGIN
			DECLARE @Produto varchar(20), @Quantidade int
	
			SELECT @Produto = Produto, @Quantidade = Quantidade FROM INSERTED
	
			INSERT INTO MOVIMENTOESTOQUE(Produto, Quantidade)
			VALUES (@Produto, @Quantidade)
	
		END
	')
END
GO
--CRIA AS STORED PROCEDURE
IF NOT EXISTS(SELECT * FROM sys.objects WHERE name = 'spInsereVenda')
BEGIN
	EXEC('
		CREATE PROCEDURE spInsereVenda 
		@Produto varchar(20),
		@Quantidade int,
		@ValorUn numeric(8,2),
		@Desconto numeric(8,2),
		@AliquotaICMS numeric(8,2),
		@Obs varchar(40)
		AS
		BEGIN
			INSERT INTO VENDA (Produto, Quantidade, ValorUn, Desconto, AliquotaICMS, Obs)
			VALUES (@Produto, @Quantidade, @ValorUn, @Desconto, @AliquotaICMS, @Obs)
		END
	')
END
GO

--CRIA OS DADOS INICIAIS

--Insere os produtos iniciais
INSERT INTO PRODUTO(Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar) VALUES ('Sal', 'Sal Iodado Chines', 1, 5)
INSERT INTO PRODUTO(Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar) VALUES ('Manteiga', 'Manteiga 320 g Pluff', 5, 10)
INSERT INTO PRODUTO(Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar) VALUES ('Leite', 'Leita Da Vaca', 5, 10)
INSERT INTO PRODUTO(Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar) VALUES ('Bolacha', 'Bolacha redonda', 3, 6)
INSERT INTO PRODUTO(Produto, NomeCompleto, QtdMiniEstoque, QtdLoteComprar) VALUES ('Trigo', 'Trigo Branco', 2, 4)
GO