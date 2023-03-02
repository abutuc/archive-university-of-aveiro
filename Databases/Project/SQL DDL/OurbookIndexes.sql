DROP INDEX IF EXISTS UTILIZADOR.idxUtilizador
DROP INDEX IF EXISTS LIVRO.idxLivro;
DROP INDEX IF EXISTS AvaliacaoBookMate.idxAvaliacaoBookMate;
DROP INDEX IF EXISTS AvaliacaoEstafeta.idxAvaliacaoEstafeta;

CREATE INDEX idxUtilizador ON UTILIZADOR(Fname, Lname);
CREATE INDEX idxLivro ON LIVRO(Nome, Autor);
CREATE INDEX idxAvaliacaoBookMate ON AvaliacaoBookMate(IDuser1, IDuser2);
CREATE INDEX idxAvaliacaoEstafeta ON AvaliacaoEstafeta(IDuser, IDestafeta);
