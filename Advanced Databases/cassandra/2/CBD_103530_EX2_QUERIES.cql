-- It doesn't make sense to create keyspace, since it must be already created from the DDL file
-- In order to be able to insert content into the several tables.
-- Use created keyspace:
USE cbd_103530_ex2;

-- EX:C:7
select * from videos_by_author where author_id=7;
-- EX:C:8
select * from comments_by_user where user_id=5 order by made_on desc;
-- EX:C:9
select * from comments_by_video where video_id=6 order by made_on desc;
-- EX:C:10
select count(rating), avg(rating) from ratings where video_id=1;
-- EX:D:1
select * from comments_by_video where video_id=6 order by made_on DESC;
-- EX:D:2
select tags from videos where video_id=1;
-- EX:D:3
select * from tags where name='Aveiro';
-- EX:D:4
select * from events_by_video_user where video_id=1 and user_id=4 order by occured_on desc limit 5;
-- EX:D:5
select * from videos_by_author where author_id=3 and shared_on > '2008-08-01T00:00:00' and shared_on < '2008-08-08T07:22:22';
-- EX:D:6
-- Esta query não pode ser feita em Cassandra pois requer que sejam retornadas todas as linhas da tabela videos para posteriormente fazer "order by"
-- No entanto, não é possível fazer um "order by" sem termos primeiro uma "partition" para ordernar, sendo que uma partition não pode ser toda a tabela.
-- EX:D:7
select * from videos_followers where video_id=7;
-- EX:D:8
-- Esta query não é possível de ser realizada em Cassandra, pois requer um join.
-- EX:D:9
-- Mesma explicação do EX:D:6
-- EX:D:10
-- Mesma explicação do EX:D:6
-- EX:D:11
-- Mesma explicação do EX:D:6
-- EX:D:12 - Listar a máxima rating que um dado vídeo recebeu.
select max(rating) from ratings where video_id=1;
-- EX:D:13 - Listar o último video publicado por um dado utilizador.
select * from videos_by_author where author_id=3 order by shared_on desc limit 1;
