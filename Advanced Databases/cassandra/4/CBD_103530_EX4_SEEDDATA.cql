-- Use created keyspace:
USE cbd_103530_ex4;

-- INSERT INTO segurancas

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        1,
        'Alfredo Linguini',
        22,
        'junior',
        {
            'Segunda-Feira': '9-10-11',
            'Terça-Feira': '',
            'Quarta-Feira': '14-15-16',
            'Quinta-Feira': '9-10-11 14-15-16',
            'Sexta-Feira': '21-22'
        },
        ['family', 'fear of failure']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        2,
        'Tom Hardy',
        34,
        'senior',
        {
            'Segunda-Feira': '',
            'Terça-Feira': '',
            'Quarta-Feira': '18-19-20-21-22-23',
            'Quinta-Feira': '',
            'Sexta-Feira': '18-19-20-21-22-23'
        },
        ['love']
    );


INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        3,
        'Samantha Hargreeves',
        35,
        'senior',
        {
            'Segunda-Feira': '9-10-11',
            'Terça-Feira': '9-10-11',
            'Quarta-Feira': '',
            'Quinta-Feira': '9-10-11',
            'Sexta-Feira': '9-10-11'
        },
        ['children', 'mother']
    );


INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        4,
        'Christopher Jack',
        18,
        'trainee',
        {
            'Segunda-Feira': '13-14-15',
            'Terça-Feira': '11-12',
            'Quarta-Feira': '17-18',
            'Quinta-Feira': '17-18',
            'Sexta-Feira': ''
        },
        ['friends']
    );


INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        5,
        'Merlin Beards',
        55,
        'supervisor',
        {
            'Segunda-Feira': '18-19',
            'Terça-Feira': '18-19',
            'Quarta-Feira': '18-19',
            'Quinta-Feira': '18-19',
            'Sexta-Feira': '18-19'
        },
        ['game addiction']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        6,
        'Steve Harlow',
        44,
        'senior',
        {
            'Segunda-Feira': '12-13-14',
            'Terça-Feira': '12-13-14',
            'Quarta-Feira': '',
            'Quinta-Feira': '12-13-14',
            'Sexta-Feira': '12-13-15'
        },
        ['divorced', 'lonely']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        7,
        'Hailey Bailey',
        45,
        'supervisor',
        {
            'Segunda-Feira': '9-10',
            'Terça-Feira': '9-10',
            'Quarta-Feira': '9-10',
            'Quinta-Feira': '9-10',
            'Sexta-Feira': '9-10'
        },
        ['fear of failure', 'childless']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        8,
        'Allan Pool',
        21,
        'junior',
        {
            'Segunda-Feira': '24-1-2-3-4',
            'Terça-Feira': '24-1-2-3-4',
            'Quarta-Feira': '24-1-2-3-4',
            'Quinta-Feira': '',
            'Sexta-Feira': '5-6-7-8'
        },
        ['gaming', 'anime']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        9,
        'Kit Harrig',
        22,
        'junior',
        {
            'Segunda-Feira': '5-6-7-8',
            'Terça-Feira': '5-6-7-8',
            'Quarta-Feira': '',
            'Quinta-Feira': '24-1-2-3-4',
            'Sexta-Feira': '24-1-2-3-4'
        },
        ['crazy ex-girlfriend']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        10,
        'Kris Jewel',
        19,
        'trainee',
        {
            'Segunda-Feira': '13-14-15',
            'Terça-Feira': '11-12',
            'Quarta-Feira': '17-18',
            'Quinta-Feira': '17-18',
            'Sexta-Feira': ''
        },
        ['insecurity', 'attention seeker']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        11,
        'Sarah Watson',
        21,
        'trainee',
        {
            'Segunda-Feira': '13-14-15',
            'Terça-Feira': '11-12',
            'Quarta-Feira': '17-18',
            'Quinta-Feira': '17-18',
            'Sexta-Feira': ''
        },
        ['dogs', 'cats']
    );

INSERT INTO segurancas(id, nome, idade, cargo, horario, fraquezas)
    VALUES(
        12,
        'Catherin Hugh',
        18,
        'trainee',
        {
            'Segunda-Feira': '13-14-15',
            'Terça-Feira': '11-12',
            'Quarta-Feira': '17-18',
            'Quinta-Feira': '17-18',
            'Sexta-Feira': ''
        },
        ['parents', 'boyfriend']
    );


-- INSERT INTO segurancas_log | log da semana de 21-25 de novembro de 2022

-- Alfredo Linguini log
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        1,
        1,
        'entrada',
        '2022-11-21T09:00:19'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        2,
        1,
        'saida',
        '2022-11-21T12:01:22'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        3,
        1,
        'entrada',
        '2022-11-23T13:58:34'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        4,
        1,
        'saida',
        '2022-11-23T17:00:52'
    );


INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        5,
        1,
        'entrada',
        '2022-11-24T09:00:34'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        6,
        1,
        'saida',
        '2022-11-24T12:02:03'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        7,
        1,
        'entrada',
        '2022-11-24T14:01:44'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        8,
        1,
        'saida',
        '2022-11-24T17:12:32'
    );


-- Tom Hardy log
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        9,
        2,
        'entrada',
        '2022-11-23T17:58:27'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        10,
        2,
        'saida',
        '2022-11-23T00:02:03'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        11,
        2,
        'entrada',
        '2022-11-25T18:00:14'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        12,
        2,
        'saida',
        '2022-11-24T00:00:39'
    );


-- Samantha Hargreeves log
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        13,
        3,
        'entrada',
        '2022-11-21T09:00:57'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        14,
        3,
        'saida',
        '2022-11-21T12:01:00'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        15,
        3,
        'entrada',
        '2022-11-22T09:01:07'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        16,
        3,
        'saida',
        '2022-11-22T12:00:10'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        17,
        3,
        'entrada',
        '2022-11-24T08:58:33'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        18,
        3,
        'saida',
        '2022-11-24T11:59:12'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        19,
        3,
        'entrada',
        '2022-11-25T09:10:05'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        20,
        3,
        'saida',
        '2022-11-25T12:15:46'
    );



-- Alfredo Linguini 25/111
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        21,
        1,
        'entrada',
        '2022-11-25T21:00:00'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        22,
        1,
        'saida',
        '2022-11-25T23:01:00'
    );


-- Christopher Jack
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        23,
        4,
        'entrada',
        '2022-11-21T13:00:01'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        24,
        4,
        'saida',
        '2022-11-21T16:01:11'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        25,
        4,
        'entrada',
        '2022-11-22T11:01:00'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        26,
        4,
        'saida',
        '2022-11-22T13:02:12'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        27,
        4,
        'entrada',
        '2022-11-23T17:01:02'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        28,
        4,
        'saida',
        '2022-11-23T19:10:27'
    );

INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        29,
        4,
        'entrada',
        '2022-11-24T17:00:18'
    );
INSERT INTO segurancas_log(id, id_seg, evento, instante)
    VALUES(
        30,
        4,
        'saida',
        '2022-11-21T19:02:00'
    );


-- ... não faz sentido colocar mais registos, daria mais de 100 inserções

-- INSERT INTO postos

INSERT INTO postos(id, nome, local)
    VALUES(
        1,
        'Polo A',
        'Secção Jardim'
    );

INSERT INTO postos(id, nome, local)
    VALUES(
        2,
        'Polo B',
        'Secção Hall'
    );

INSERT INTO postos(id, nome, local)
    VALUES(
        3,
        'Polo C',
        'Secção Portão'
    );

INSERT INTO postos(id, nome, local)
    VALUES(
        4,
        'Polo D',
        'Secção Gabinete'
    );


-- INSERT INTO camaras

-- Local: Jardim
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        1,
        'Jardim',
        'ligada',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        2,
        'Jardim',
        'ligada',
        {'720p', '30fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        3,
        'Jardim',
        'desligada',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        4,
        'Jardim',
        'manutenção',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        5,
        'Jardim',
        'estragada',
        {'480p'}
    );


-- Local: Hall
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        6,
        'Hall',
        'ligada',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        7,
        'Hall',
        'ligada',
        {'1080p', '30fps'}
    );

INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        8,
        'Hall',
        'ligada',
        {'1080p'}
    );
    
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        9,
        'Hall',
        'manutenção',
        {'1080p', '60fps'}
    );

-- Local: Portão
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        10,
        'Portão',
        'ligada',
        {'720p'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        11,
        'Portão',
        'desligada',
        {'1080p', '30fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        12,
        'Portão',
        'estragada',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        13,
        'Portão',
        'estragada',
        {'1080p'}
    );

-- Local: Gabinete
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        14,
        'Gabinete',
        'ligada',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        15,
        'Gabinte',
        'ligada',
        {'720p', '30fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        16,
        'Gabinete',
        'desligada',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        17,
        'Gabinete',
        'manutenção',
        {'1080p', '60fps'}
    );
INSERT INTO camaras(id, local, estado, configuracoes)
    VALUES(
        18,
        'Gabinete',
        'estragada',
        {'480p'}
    );


-- INSERT INTO posto_segurancas

-- Posto id=1, nome= Polo A
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        1,
        'Polo A',
        1,
        'Alfredo Linguini'
    );
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        1,
        'Polo A',
        2,
        'Tom Hardy'
    );
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        1,
        'Polo A',
        3,
        'Samantha Hargreeves'
    );


-- Posto id=2, nome=Polo B
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        2,
        'Polo B',
        4,
        'Christopher Jack'
    );

INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        2,
        'Polo B',
        5,
        'Merlin Beards'
    );
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        2,
        'Polo B',
        6,
        'Steve Harlow'
    );


-- Posto id=3, nome=Polo C
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        3,
        'Polo C',
        7,
        'Hailey Bailey'
    );
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        3,
        'Polo C',
        8,
        'Allan Pool'
    );

INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        3,
        'Polo C',
        9,
        'Kit Harrig'
    );


-- Posto id=4, nome Polo D
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        4,
        'Polo D',
        10,
        'Kris Jewel'
    );
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        4,
        'Polo D',
        11,
        'Sarah Watson'
    );
INSERT INTO posto_segurancas(posto_id, posto_name, seg_id, seg_nome)
    VALUES(
        4,
        'Polo D',
        12,
        'Catherin Hugh'
    );


-- INSERT INTO posto_camaras
-- Posto id=1, nome=Polo A, local: Jardim 
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        1,
        'Polo A',
        1,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        1,
        'Polo A',
        2,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        1,
        'Polo A',
        3,
        'desligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        1,
        'Polo A',
        4,
        'manutenção'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        1,
        'Polo A',
        5,
        'estragada'
    );


-- Posto id=2, nome=Polo B, local: Hall 

INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        2,
        'Polo B',
        6,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        2,
        'Polo B',
        7,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        2,
        'Polo B',
        8,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        2,
        'Polo B',
        9,
        'manutenção'
    );


-- Posto id=3, nome=Polo C, local: Portão 

INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        3,
        'Polo C',
        10,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        3,
        'Polo C',
        11,
        'desligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        3,
        'Polo C',
        12,
        'estragada'
    );

-- Posto id=4, nome=Polo D, local: Gabinete 

INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        4,
        'Polo D',
        13,
        'estragada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        4,
        'Polo D',
        14,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        4,
        'Polo D',
        15,
        'ligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        4,
        'Polo D',
        16,
        'desligada'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        4,
        'Polo D',
        17,
        'manutenção'
    );
INSERT INTO posto_camaras(posto_id, posto_name, camara_id, camara_estado)
    VALUES(
        4,
        'Polo D',
        18,
        'estragada'
    );


-- INSERT INTO camara_eventos, eventos registados no dia 21/11/2022 pelas camaras 1 e 2

-- camara_id=1
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        1,
        '2022-11-21T9:15:13',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        2,
        '2022-11-21T9:35:42',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        3,
        '2022-11-21T10:25:57',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        4,
        '2022-11-21T11:45:05',
        'suspeito'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        5,
        '2022-11-21T14:01:11',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        6,
        '2022-11-21T16:07:08',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        7,
        '2022-11-21T19:33:48',
        'suspeito'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        8,
        '2022-11-21T9:33:59',
        'alerta'
    );


-- camara_id=2

INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        9,
        '2022-11-21T8:10:13',
        'suspeito'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        10,
        '2022-11-21T8:30:21',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        11,
        '2022-11-21T8:45:32',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        12,
        '2022-11-21T9:03:13',
        'alerta'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        13,
        '2022-11-21T9:15:23',
        'alerta'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        14,
        '2022-11-21T10:25:34',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        15,
        '2022-11-21T11:15:43',
        'suspeito'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        16,
        '2022-11-21T12:04:04',
        'suspeito'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        17,
        '2022-11-21T13:13:55',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        18,
        '2022-11-21T16:44:44',
        'suspeito'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        19,
        '2022-11-21T18:22:22',
        'normal'
    );
INSERT INTO camara_eventos (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        20,
        '2022-11-21T19:22:22',
        'normal'
    );
    

-- INSERT INTO camara_por_estado
-- Local: Jardim
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        1,
        'Jardim',
        'ligada',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        2,
        'Jardim',
        'ligada',
        {'720p', '30fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        3,
        'Jardim',
        'desligada',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        4,
        'Jardim',
        'manutenção',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        5,
        'Jardim',
        'estragada',
        {'480p'}
    );


-- Local: Hall
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        6,
        'Hall',
        'ligada',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        7,
        'Hall',
        'ligada',
        {'1080p', '30fps'}
    );

INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        8,
        'Hall',
        'ligada',
        {'1080p'}
    );
    
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        9,
        'Hall',
        'manutenção',
        {'1080p', '60fps'}
    );

-- Local: Portão
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        10,
        'Portão',
        'ligada',
        {'720p'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        11,
        'Portão',
        'desligada',
        {'1080p', '30fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        12,
        'Portão',
        'estragada',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        13,
        'Portão',
        'estragada',
        {'1080p'}
    );

-- Local: Gabinete
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        14,
        'Gabinete',
        'ligada',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        15,
        'Gabinte',
        'ligada',
        {'720p', '30fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        16,
        'Gabinete',
        'desligada',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        17,
        'Gabinete',
        'manutenção',
        {'1080p', '60fps'}
    );
INSERT INTO camara_por_estado(id, local, estado, configuracoes)
    VALUES(
        18,
        'Gabinete',
        'estragada',
        {'480p'}
    );

-- INSERT INTO camara_eventos_por_timestp

-- camara_id=1
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        1,
        '2022-11-21T9:15:13',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        2,
        '2022-11-21T9:35:42',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        3,
        '2022-11-21T10:25:57',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        4,
        '2022-11-21T11:45:05',
        'suspeito'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        5,
        '2022-11-21T14:01:11',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        6,
        '2022-11-21T16:07:08',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        7,
        '2022-11-21T19:33:48',
        'suspeito'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        8,
        '2022-11-21T9:33:59',
        'alerta'
    );


-- camara_id=2

INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        9,
        '2022-11-21T8:10:13',
        'suspeito'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        10,
        '2022-11-21T8:30:21',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        11,
        '2022-11-21T8:45:32',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        12,
        '2022-11-21T9:03:13',
        'alerta'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        13,
        '2022-11-21T9:15:23',
        'alerta'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        14,
        '2022-11-21T10:25:34',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        15,
        '2022-11-21T11:15:43',
        'suspeito'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        16,
        '2022-11-21T12:04:04',
        'suspeito'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        17,
        '2022-11-21T13:13:55',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        18,
        '2022-11-21T16:44:44',
        'suspeito'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        19,
        '2022-11-21T18:22:22',
        'normal'
    );
INSERT INTO camara_eventos_por_timestp (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        20,
        '2022-11-21T19:22:22',
        'normal'
    );


-- INSERT INTO camara_eventos_por_tipo

-- camara_id=1
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        1,
        '2022-11-21T9:15:13',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        2,
        '2022-11-21T9:35:42',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        3,
        '2022-11-21T10:25:57',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        4,
        '2022-11-21T11:45:05',
        'suspeito'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        5,
        '2022-11-21T14:01:11',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        6,
        '2022-11-21T16:07:08',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        7,
        '2022-11-21T19:33:48',
        'suspeito'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        1,
        8,
        '2022-11-21T9:33:59',
        'alerta'
    );


-- camara_id=2

INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        9,
        '2022-11-21T8:10:13',
        'suspeito'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        10,
        '2022-11-21T8:30:21',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        11,
        '2022-11-21T8:45:32',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        12,
        '2022-11-21T9:03:13',
        'alerta'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        13,
        '2022-11-21T9:15:23',
        'alerta'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        14,
        '2022-11-21T10:25:34',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        15,
        '2022-11-21T11:15:43',
        'suspeito'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        16,
        '2022-11-21T12:04:04',
        'suspeito'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        17,
        '2022-11-21T13:13:55',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        18,
        '2022-11-21T16:44:44',
        'suspeito'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        19,
        '2022-11-21T18:22:22',
        'normal'
    );
INSERT INTO camara_eventos_por_tipo (camara_id, evento_id, timestp, tipo)
    VALUES(
        2,
        20,
        '2022-11-21T19:22:22',
        'normal'
    );