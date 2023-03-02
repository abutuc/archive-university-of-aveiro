DROP PROCEDURE IF EXISTS UserStatistics
DROP PROCEDURE IF EXISTS RemoveBook
DROP PROCEDURE IF EXISTS RemoveUser
DROP PROCEDURE IF EXISTS GetUserList
DROP PROCEDURE IF EXISTS GetMaxUserID
DROP PROCEDURE IF EXISTS CreateNewUser
DROP PROCEDURE IF EXISTS UpdateUser
DROP PROCEDURE IF EXISTS GetEstafetaList
DROP PROCEDURE IF EXISTS GetMaxEstafetaID
DROP PROCEDURE IF EXISTS CreateNewEstafeta
DROP PROCEDURE IF EXISTS UpdateEstafeta
DROP PROCEDURE IF EXISTS RemoveEstafeta
DROP PROCEDURE IF EXISTS CreateNewBook
DROP PROCEDURE IF EXISTS GetMaxLivroCode
DROP PROCEDURE IF EXISTS GetBookList
DROP PROCEDURE IF EXISTS CreateNewFormularioDeposito
DROP PROCEDURE IF EXISTS CreateNewFormularioTroca
DROP PROCEDURE IF EXISTS GetMaxFormID

-- procedure used to get number of books and number of exchanges of User
GO
CREATE PROC UserStatistics @UserID int
AS
	SELECT NumberOfDeposits, COUNT(ID) AS NumberOfExchanges FROM (
	SELECT COUNT(FORMULARIO_DEPOSITO.CodigoLivro) AS NumberOfDeposits, FORMULARIO_TROCA.ID FROM 
	((UTILIZADOR JOIN FORMULARIO_DEPOSITO ON UTILIZADOR.ID=IDuser) 
	LEFT JOIN FORMULARIO_TROCA 
	ON (UTILIZADOR.ID = FORMULARIO_TROCA.IDuser1 OR UTILIZADOR.ID = FORMULARIO_TROCA.IDuser2))
	WHERE UTILIZADOR.ID = @UserID GROUP BY FORMULARIO_TROCA.ID) AS Result GROUP BY NumberOfDeposits;
GO

-- procedure used to remove Book instance or to remove Books associated with a particular User
GO
CREATE PROC RemoveBook @UserID int = NULL, @BookCode int = NULL
AS
	IF @UserID IS NULL AND @BookCode IS NULL
	BEGIN
		PRINT 'Must pass at least one parameter'
		RETURN 
	END
	
	IF @UserID IS NOT NULL
	BEGIN
		SELECT Codigo Into #CodigoLivros FROM dbo.LIVRO WHERE IDuser = @UserID
		DECLARE @Codigo INT;
		WHILE (Select Count(*) From #CodigoLivros) > 0
		Begin
			Select Top 1 @Codigo = Codigo From #CodigoLivros
			DELETE FROM dbo.FOTOS_DEPOSITO WHERE LivroCodigo=@Codigo
			DELETE #CodigoLivros WHERE Codigo=@Codigo
		END
		DELETE FROM dbo.LIVRO WHERE IDuser = @UserID
	END

	IF @BookCode IS NOT NULL
	BEGIN
		DELETE FROM dbo.LIVRO WHERE Codigo=@BookCode
	END
GO


-- procedure used to remove a User
GO
CREATE PROC RemoveUser @UserID int 
AS
	-- remove User from dependent tables
	DELETE FROM dbo.PREFERENCIAS_LITERARIAS WHERE IDuser = @UserID;
	DELETE FROM dbo.INTEGRA_COMUNIDADE WHERE IDuser = @UserID;
	DELETE FROM dbo.PERFIL WHERE IDuser = @UserID;
	DELETE FROM dbo.FORMULARIO_DEPOSITO WHERE IDuser = @UserID;
	DELETE FROM dbo.FORMULARIO_TROCA WHERE IDuser1 = @UserID;
	EXEC RemoveBook @UserID;
	DELETE FROM dbo.AvaliacaoBookMate WHERE IDuser1=@UserID;
	DELETE FROM dbo.AvaliacaoEstafeta WHERE IDuser = @UserID;
	DELETE FROM dbo.DaGorjeta WHERE IDuser = @UserID;
	UPDATE dbo.FORMULARIO_TROCA SET IDuser2 = NULL WHERE IDuser2 = @UserID;
	UPDATE dbo.AvaliacaoBookMate SET IDuser2 = NULL WHERE IDuser2 = @UserID;
	DELETE FROM dbo.UTILIZADOR WHERE ID = @UserID;
GO


-- procedure used to get User List
GO
CREATE PROC GetUserList 
AS
	SELECT * FROM dbo.UTILIZADOR
GO


-- procedure used to get the user max ID
GO
CREATE PROC GetMaxUserID
AS
	SELECT MAX(ID) AS MAX_ID FROM dbo.UTILIZADOR
GO

-- procedure used to Create a New User, inserting the User into the UTILIZADOR table
GO
CREATE PROC CreateNewUser @ID int, @Fname varchar(30), @Lname varchar(30), @Username varchar(30), @Email varchar(30), @Birthday date
AS 
	INSERT dbo.UTILIZADOR (ID, Fname, Lname, Username, Email, Birthday) VALUES (@ID, @Fname, @Lname, @Username, @Email, @Birthday);
GO

-- procedure used to Update User Information
GO
CREATE PROC UpdateUser @ID int, @Fname varchar(30), @Lname varchar(30), @Username varchar(30), @Email varchar(30), @Birthday date
AS
	UPDATE dbo.UTILIZADOR SET Fname = @Fname, Lname = @Lname, Username = @Username, Email = @Email, Birthday = @Birthday
		WHERE ID = @ID
GO


-- procedure used to get Estafeta List
GO
CREATE PROC GetEstafetaList
AS
	SELECT * FROM dbo.ESTAFETA
GO

GO

-- procedure used to get Max Estafeta ID
CREATE PROC GetMaxEstafetaID
AS
	SELECT MAX(ID) AS MAX_ID FROM dbo.ESTAFETA
GO

-- procedure used to Create a New Estafeta, inserting the Estafeta into the ESTAFETA table
GO
CREATE PROC CreateNewEstafeta @ID int, @Name varchar(30)
AS 
	INSERT dbo.ESTAFETA(ID, Nome) VALUES (@ID, @Name);
GO

-- procedure used to Update Estafeta Information
GO
CREATE PROC UpdateEstafeta @ID int, @Name varchar(30)
AS
	UPDATE dbo.ESTAFETA SET Nome = @Name WHERE ID = @ID
GO


-- procedure used to remove a Estafeta
GO
CREATE PROC RemoveEstafeta @ID int
AS
	DELETE FROM dbo.DaGorjeta WHERE IDestafeta = @ID
	UPDATE dbo.FORMULARIO_TROCA SET IDestafeta = NULL WHERE IDestafeta = @ID
	UPDATE dbo.AvaliacaoEstafeta SET IDestafeta = NULL WHERE IDestafeta = @ID
	DELETE FROM dbo.ESTAFETA WHERE ID=@ID
GO


-- procedure used to Create a New Book, inserting the Book into the LIVRO table
GO
CREATE PROC CreateNewBook @IDuser int, @Codigo int, @Nome varchar(100), @Autor varchar(100), @Edicao int, @Editora varchar(100)
AS	
	INSERT dbo.LIVRO(IDuser, Codigo, Nome, Autor, Edicao, Editora) VALUES (@IDuser, @Codigo, @Nome, @Autor, @Edicao, @Editora);
GO

-- procedure used to get Max Livro Code
CREATE PROC GetMaxLivroCode
AS
	SELECT MAX(Codigo) AS MAX_CODE FROM dbo.LIVRO
GO


-- procedure used to get Livro List of IDUser
GO
CREATE PROC GetBookList @IDUser int
AS
	SELECT * FROM dbo.LIVRO WHERE IDuser = @IDUser
GO


GO
CREATE PROC CreateNewFormularioDeposito @IDuser int, @CodigoLivro int
AS	
	DECLARE @IDform INT;
	SELECT Top 1 @IDform = MAX_ID FROM (SELECT MAX(ID) AS MAX_ID FROM dbo.FORMULARIO_DEPOSITO) AS Result
	IF @IDform IS NULL
		BEGIN
		SET @IDForm = 0;
		END
	INSERT INTO dbo.FORMULARIO_DEPOSITO(IDuser, ID, Data, CodigoLivro) VALUES (@IDuser, @IDform + 1, GETDATE(), @CodigoLivro)
GO


GO
CREATE PROC CreateNewFormularioTroca @IDuser1 INT, @IDuser2 INT, @ID INT, @Data DATE, @DataDeTroca DATE, @IDestafeta INT, @CodigoLivro INT
	AS
	INSERT INTO dbo.FORMULARIO_TROCA(IDuser1, IDuser2, ID, Data, DataDeTroca, IDestafeta, CodigoLivro) 
	VALUES (@IDuser1, @IDuser2, @ID, @Data, @DataDeTroca, @IDestafeta, @CodigoLivro)
GO

GO
CREATE PROC GetMaxFormID
	AS
	SELECT MAX(ID) AS MAX_ID FROM dbo.FORMULARIO_TROCA
GO