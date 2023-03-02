DROP TRIGGER IF EXISTS bookDepositted
GO
CREATE TRIGGER bookDepositted ON dbo.LIVRO AFTER INSERT
	AS
	DECLARE @UserID int
	DECLARE @BookCode int
	SELECT @UserID = IDuser, @BookCode = Codigo FROM inserted;
	EXEC CreateNewFormularioDeposito @UserID, @BookCode
GO