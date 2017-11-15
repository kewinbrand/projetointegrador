CREATE OR ALTER spInsereVenda 
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
GO