CREATE TABLE PRODUTO(
  Produto varchar(20) not null,
  NomeCompleto varchar(100),
  QtdMiniEstoque int,
  QtdLoteComprar int,
  
  CONSTRAINT PK_PRODUTO PRIMARY KEY (Produto)
)
GO

CREATE TABLE VENDA(
  Produto varchar(20),
  Quantidade int,
  ValorUn numeric(8,2),
  Desconto numeric(8,2),
  AliquotaICMS numeric(8,2),
  Obs varchar(40)  
  
  CONSTRAINT FK_VENDA_PRODUTO FOREIGN KEY (Produto) REFERENCES Produto(Produto)
)
GO

CREATE TABLE MOVIMENTOESTOQUE(
  IdMovimento int not null identity(1,1),
  DataHora datetime DEFAULT GETDATE(),
  Produto varchar(20),
  Quantidade int  
  
  CONSTRAINT FK_MOVIMENTOESTOQUE_PRODUTO FOREIGN KEY (Produto) REFERENCES Produto(Produto)  
)
GO