-- First to create my keyspace:
CREATE KEYSPACE IF NOT EXISTS cbd_103530_ex4 WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1};
-- Use created keyspace:
USE cbd_103530_ex4;

-- Create segurancas table:
CREATE TABLE segurancas (
    id int,
    nome text,
    idade int,
    cargo text,
    horario map<text, text>,
    fraquezas list<text>,
    PRIMARY KEY(id)
);

-- Create secondary index on segurancas table | usada na Query 5
CREATE INDEX segname on segurancas (nome);

-- Create secondary index on segurancas table | usada na Query 10
CREATE INDEX segcargo on segurancas (cargo);

-- Create seguranças log table || utilizado para a Query 3
CREATE TABLE segurancas_log (
    id int,
    id_seg int,
    evento text,
    instante timestamp,
    PRIMARY KEY(id_seg, evento, instante, id)
);


-- Create postos table:
CREATE TABLE postos (
    id int,
    nome text,
    local text,
    PRIMARY KEY(id)
);

-- Create camaras table:
CREATE TABLE camaras (
    id int,
    local text,
    estado text,
    configuracoes set<text>,
    PRIMARY KEY(id)
);

-- Create secondary index on camaras | usada na QUery 7
CREATE INDEX camlocal on camaras (local);


-- Create table posto_segurancas:
CREATE TABLE posto_segurancas(
    posto_id int,
    posto_name text,
    seg_id int,
    seg_nome text,
    PRIMARY KEY(posto_id, seg_id)
);


-- Create table posto_camaras:
CREATE TABLE posto_camaras(
    posto_id int,
    posto_name text,
    camara_id int,
    camara_estado text,
    PRIMARY KEY(posto_id, camara_id)
);

-- Create secondary index on posto_camaras table | usada na query 9
CREATE INDEX pcestado on posto_camaras(camara_estado);

-- Create table camara_eventos:
CREATE TABLE camara_eventos(
    camara_id int,
    evento_id int,
    timestp timestamp,
    tipo text,
    PRIMARY KEY(camara_id, evento_id)
);

-- Query 1
CREATE TABLE camara_por_estado(
    id int,
    local text,
    estado text,
    configuracoes set<text>,
    PRIMARY KEY(estado, id)
);

-- Query 2
CREATE TABLE camara_eventos_por_timestp(
    camara_id int,
    evento_id int,
    timestp timestamp,
    tipo text,
    PRIMARY KEY(camara_id, timestp, evento_id)
);

-- Query 8
CREATE TABLE camara_eventos_por_tipo(
    camara_id int,
    evento_id int,
    timestp timestamp,
    tipo text,
    PRIMARY KEY(camara_id, tipo, evento_id)
);

