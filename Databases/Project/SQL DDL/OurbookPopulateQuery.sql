DELETE FROM dbo.PREFERENCIAS_LITERARIAS;
DELETE FROM dbo.INTEGRA_COMUNIDADE;
DELETE FROM dbo.COMUNIDADE;
DELETE FROM dbo.PERFIL;
DELETE FROM dbo.FOTOS_DEPOSITO;
DELETE FROM dbo.FORMULARIO_DEPOSITO
DELETE FROM dbo.FORMULARIO_TROCA
DELETE FROM dbo.AvaliacaoBookMate;
DELETE FROM dbo.AvaliacaoEstafeta;
DELETE FROM dbo.DaGorjeta;
DELETE FROM dbo.ESTAFETA;
DELETE FROM dbo.LIVRO;
DELETE FROM dbo.UTILIZADOR;


INSERT INTO dbo.UTILIZADOR
VALUES
(1, 'Malachi', 'Stafford', 'booksunstone', 'malachi.stafford@gmail.pt', '1991-05-12', 'Lat: 2.20 Long: -156.86'),
(2, 'Amrit', 'Kay', 'bookyogurt', 'kamrit@ua.pt', '2002-01-01', 'Lat: 4.16 Long: 140.41'),
(3, 'Rocky', 'Shelton', 'shelton', 'sulon@alvinneo.com', '1999-12-20', 'Lat: 44.16 Long: 100.41'),
(4, 'Humaira', 'Wolfe', 'humanvegamir', 'degio@debb.me', '2003-04-13', 'Lat: 0.1 Long: 10.1'),
(5, 'Nikki', 'Leonard', 'leoleopard', 'pros@victorysvg.com', '1980-03-23', 'Lat: 16 Long: 10.41'),
(6, 'Alexis', 'Chambers', 'ALEXISantrye', 'juice134@roptaoti.com', '2000-01-17', 'Lat: 2.20 Long: -16.86'),
(7, 'Morgan', 'Dolan', 'morgantwhale', 'masya19951995@rev3.cf', '1988-03-23', 'Lat: 33.22 Long: 99.99'),
(8, 'Lucia', 'Hampton', 'lucy', 'graffdyuma@proxy4gs.com', '2000-04-17', 'Lat: 3.22 Long: 9.99'),
(9, 'Yasmeen', 'Eastwood', 'meenwood', 'silins@emvil.com', '2001-03-11', 'Lat: 222 Long: 102'),
(10, 'Don', 'Holman', 'holddon', 'olesyapolitko@techholic.in', '1996-11-09', 'Lat: 222 Long: 102');

INSERT INTO dbo.LIVRO VALUES (1, 1, 'The Art Of War', 'Sun Tzu', 1, 'Wellfleet Press');
INSERT INTO dbo.LIVRO VALUES (1, 2, 'Rei do Bluff', 'Afonso Noite-Luar', 1, 'Manuscrito Editora');
INSERT INTO dbo.LIVRO VALUES (4, 3, 'Na Cabeça de Putin', 'Michel Eltchaninoff', 1, 'Livros Zigurate');
INSERT INTO dbo.LIVRO VALUES (10, 4, 'Diário de um Sem-Abrigo', 'Jorge Costa', 2, 'Oficina do Livro');

INSERT INTO dbo.ESTAFETA
VALUES
(1, 'Alberto Cromatídeo', 'Lat: 50 Long: 100'),
(2, 'Constantin Strubal', 'Lat: 0 Long: 0'),
(3, 'Alfredo Alves', 'Lat: 25 Long: 20'),
(4, 'Sara LeMarc', 'Lat: 12 Long: 12');

INSERT INTO dbo.DaGorjeta
VALUES
(2, 3, 6.5, '2022-06-01'),
(3, 3, 7.5, '2022-06-03'),
(10, 1, 3.25, '2022-06-08');


INSERT INTO dbo.AvaliacaoEstafeta
VALUES
(1, 1, 'O senhor Alberto é um ótimo estafeta, muito pontual e competente', '2022-06-03', 1),
(3, 2, 'Sara LeMarc esqueceu-se do meu livro!!', '2022-06-04', 4);

INSERT INTO dbo.AvaliacaoBookMate
VALUES
(1, 2, 'AB01', 'blah blaaaaa', '2022-02-22'),
(3, 10, 'AB02', 'blah blaaaaa2', '2022-04-22'),
(6, 6, 'AB03', 'blah blaaaaa', '2022-07-22'),
(1, 2, 'AB04', 'blah blaaaaa', '2022-12-22');

INSERT INTO dbo.FORMULARIO_TROCA
VALUES
(1, 2, 1, '2022-07-12', '2022-07-15', 1, 1),
(10, 7, 2,'2022-06-08', '2022-08-04', 2, 4);


INSERT INTO dbo.FOTOS_DEPOSITO
VALUES
(1, '/path/to/folder/with/Book1'),
(2, '/path/folder/with/Book2'),
(3, '/path/folder/with/Book3'),
(4, '/path/folder/with/Book4');

INSERT INTO dbo.PERFIL
VALUES
(1, 'www.ourbook.com/booksunstone/profile', 'path/to/image/user/1', 'Something something I am me'),
(2, 'www.ourbook.com/bookyogurt/profile', 'path/to/image/user/2', 'In the ocean something is salty'),
(3, 'www.ourbook.com/shelton/profile', 'path/to/image/user/3', 'Spring is my favorite flavor'),
(4, 'www.ourbook.com/humanvegamir/profile', 'path/to/image/user/4', 'Nah nah not good'),
(5, 'www.ourbook.com/leoleopard/profile', 'path/to/image/user/5', 'Do I wanna Know?'),
(6, 'www.ourbook.com/ALEXISantrye/profile', 'path/to/image/user/6', 'Sometimes I stay up watching the rainbow in the high sky'),
(7, 'www.ourbook.com/morgantwhale/profile', 'path/to/image/user/7', 'Something eheheh'),
(8, 'www.ourbook.com/lucy/profile', 'path/to/image/user/8', 'Something something I am me'),
(9, 'www.ourbook.com/meenwood/profile', 'path/to/image/user/9', 'Something something I am me'),
(10, 'www.ourbook.com/holddon/profile', 'path/to/image/user/10', 'Something something I am me');

INSERT INTO dbo.COMUNIDADE
VALUES
(1, 'Adventure Community', 'Adventure'),
(2, 'Lord of the Rings Fans', 'Fantasy'),
(3, 'Financial State of the World', 'Famous');

INSERT INTO dbo.INTEGRA_COMUNIDADE
VALUES
(1, 1),
(1, 2),
(10, 3),
(9, 2);

INSERT INTO dbo.PREFERENCIAS_LITERARIAS
VALUES
(1, 'Adventure'),
(1, 'Fantasy'),
(2, 'Famous'),
(2, 'Fictional'),
(2, 'Historic'),
(3, 'Sports'),
(3, 'Humanity'),
(4, 'Famous'),
(4, 'Sports'),
(5, 'Adventure'),
(6, 'Famous'),
(7, 'Historic'),
(8, 'Sports'),
(9, 'Fantasy'),
(10, 'Revolutionary Christianism');