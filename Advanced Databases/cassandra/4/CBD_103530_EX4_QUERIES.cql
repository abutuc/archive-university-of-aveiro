-- Use created keyspace:
USE cbd_103530_ex4;

-- QUERIES

-- Q1: Listar as camaras que se encontram ligadas
select * from camara_por_estado where estado='ligada';

-- Q2: Listar os últimos 5 eventos registados pela camara com id igual a 2;
select * from camara_eventos_por_timestp where camara_id=2 order by timestp desc limit 5;

-- Q3: Listar o log de entradas e saidas do segurança com id igual a 1;
select * from segurancas_log where id_seg = 1;

-- Q4: Listar os seguranças que trabalham no posto Polo A (id = 1) ordenados pelos ids dos seguranças;
select * from posto_segurancas where posto_id=1 order by seg_id;

-- Q5: Listar o horário do seguranca que tem como nome Alfredo Linguini;
select nome, horario from segurancas where nome='Alfredo Linguini';

-- Q6: Listar o instante correspondente à ultima entrada do segurança que tem como id 3;
select id_seg, evento, instante from segurancas_log where id_seg=3 and evento='entrada' order by instante desc limit 1;

-- Q7: Listar a lista de configuracoes das camaras que se encontram no local 'Jardim';
select id, local, configuracoes from camaras where local='Jardim'; 

-- Q8: Listar os eventos suspeitos captados pela camara com id igual a 2;
select * from camara_eventos_por_tipo where camara_id=2 and tipo='suspeito';

-- Q9: Listar as camaras que se encontram desligadas no posto com id=3;
 select * from posto_camaras where posto_id =3 and camara_estado='desligada';

-- Q10: Listar todos supervisores;
select id, nome from segurancas where cargo='supervisor';
