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
GO

CREATE TRIGGER (trVerificaNecessidadeReposicao)
ON [MOVIMENTOESTOQUE]
AFTER INSERT
AS
BEGIN
	DECLARE @Produto varchar(20), @Quantidade int
	
	SELECT @Produto = Produto, @Quantidade = Quantidade FROM INSERTED
	
	INSERT INTO MOVIMENTOESTOQUE(Produto, Quantidade)
	VALUES (Produto, Quantidade)
	
END
GO


