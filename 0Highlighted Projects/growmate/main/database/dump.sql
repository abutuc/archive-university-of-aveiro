\connect db

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: air_quality_measurement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.air_quality_measurement (
    id bigint NOT NULL,
    measurement double precision,
    post_date timestamp without time zone,
    sensor_id bigint NOT NULL
);


ALTER TABLE public.air_quality_measurement OWNER TO postgres;

--
-- Name: air_quality_measurement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.air_quality_measurement_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.air_quality_measurement_seq OWNER TO postgres;

--
-- Name: air_temperature_measurement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.air_temperature_measurement (
    id bigint NOT NULL,
    measurement double precision,
    post_date timestamp without time zone,
    sensor_id bigint NOT NULL
);


ALTER TABLE public.air_temperature_measurement OWNER TO postgres;

--
-- Name: air_temperature_measurement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.air_temperature_measurement_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.air_temperature_measurement_seq OWNER TO postgres;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id bigint NOT NULL,
    post_date timestamp without time zone,
    text character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    plant_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: comments_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comments_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_seq OWNER TO postgres;

--
-- Name: disease; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disease (
    id bigint NOT NULL,
    common_name character varying(255),
    description character varying(255),
    scientific_name character varying(255),
    solution character varying(255)
);


ALTER TABLE public.disease OWNER TO postgres;

--
-- Name: disease_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.disease_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.disease_seq OWNER TO postgres;

--
-- Name: disease_species; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disease_species (
    species_id bigint NOT NULL,
    disease_id bigint NOT NULL
);


ALTER TABLE public.disease_species OWNER TO postgres;

--
-- Name: division; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.division (
    id bigint NOT NULL,
    luminosity integer,
    name character varying(255) NOT NULL,
    user_id bigint
);


ALTER TABLE public.division OWNER TO postgres;

--
-- Name: division_sensor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.division_sensor (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    sensor_code character varying(255) NOT NULL,
    division_id bigint,
    user_id bigint NOT NULL
);


ALTER TABLE public.division_sensor OWNER TO postgres;

--
-- Name: division_sensor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.division_sensor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.division_sensor_seq OWNER TO postgres;

--
-- Name: division_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.division_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.division_seq OWNER TO postgres;

--
-- Name: journal_entry; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal_entry (
    id bigint NOT NULL,
    photo character varying(255),
    post_date timestamp without time zone,
    text character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    plant_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.journal_entry OWNER TO postgres;

--
-- Name: journal_entry_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_entry_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_entry_seq OWNER TO postgres;

--
-- Name: plant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plant (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    plant_condition integer,
    photo character varying(255),
    plantation_date date,
    division_id bigint,
    user_id bigint NOT NULL,
    species_id bigint NOT NULL
);


ALTER TABLE public.plant OWNER TO postgres;

--
-- Name: plant_sensor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plant_sensor (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    sensor_code character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    plant_id bigint
);


ALTER TABLE public.plant_sensor OWNER TO postgres;

--
-- Name: plant_sensor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plant_sensor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.plant_sensor_seq OWNER TO postgres;

--
-- Name: plant_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plant_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.plant_seq OWNER TO postgres;

--
-- Name: plant_species; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plant_species (
    id bigint NOT NULL,
    common_name character varying(255),
    cycle character varying(255),
    difficulty integer,
    flowering boolean,
    leaf_color character varying(255),
    optimal_humidity integer,
    optimal_luminosity smallint,
    optimal_temperature integer,
    scientific_name character varying(255),
    season integer,
    photo character varying(500),
    usual_size double precision,
    watering_frequency integer,
    family_id bigint NOT NULL,
    CONSTRAINT plant_species_difficulty_check CHECK (((difficulty <= 5) AND (difficulty >= 1)))
);


ALTER TABLE public.plant_species OWNER TO postgres;

--
-- Name: plant_species_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plant_species_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.plant_species_seq OWNER TO postgres;

--
-- Name: reaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reaction (
    reaction_date timestamp without time zone,
    type boolean,
    user_id bigint NOT NULL,
    comment_id bigint NOT NULL
);


ALTER TABLE public.reaction OWNER TO postgres;

--
-- Name: soil_quality_measurement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.soil_quality_measurement (
    id bigint NOT NULL,
    measurement double precision,
    post_date timestamp without time zone,
    sensor_id bigint NOT NULL
);


ALTER TABLE public.soil_quality_measurement OWNER TO postgres;

--
-- Name: soil_quality_measurement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.soil_quality_measurement_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.soil_quality_measurement_seq OWNER TO postgres;

--
-- Name: species_family; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.species_family (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    difficulty integer,
    opt_soil_mix integer,
    photo character varying(255)
);


ALTER TABLE public.species_family OWNER TO postgres;

--
-- Name: species_family_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.species_family_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.species_family_seq OWNER TO postgres;

--
-- Name: task_settings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task_settings (
    id bigint NOT NULL,
    is_automatic boolean,
    task_frequency integer,
    task_type integer,
    plant_id bigint
);


ALTER TABLE public.task_settings OWNER TO postgres;

--
-- Name: task_settings_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_settings_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_settings_seq OWNER TO postgres;

--
-- Name: tasks_current; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks_current (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    task_date date,
    task_type integer,
    plant_id bigint
);


ALTER TABLE public.tasks_current OWNER TO postgres;

--
-- Name: tasks_current_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tasks_current_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tasks_current_seq OWNER TO postgres;

--
-- Name: tasks_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks_history (
    id bigint NOT NULL,
    task_date date,
    name character varying(255) NOT NULL,
    task_type integer,
    plant_id bigint
);


ALTER TABLE public.tasks_history OWNER TO postgres;

--
-- Name: tasks_history_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tasks_history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tasks_history_seq OWNER TO postgres;

--
-- Name: utilizador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilizador (
    id bigint NOT NULL,
    address character varying(255),
    dob date,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    profile_photo character varying(255),
    rating integer DEFAULT 3.0,
    experience integer DEFAULT 1,
    dead_plant_count integer DEFAULT 0,
    user_type integer
);


ALTER TABLE public.utilizador OWNER TO postgres;

--
-- Name: utilizador_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.utilizador_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utilizador_seq OWNER TO postgres;

--
-- Name: air_quality_measurement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.air_quality_measurement_seq', 1, false);


--
-- Name: air_temperature_measurement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.air_temperature_measurement_seq', 1, false);


--
-- Name: comments_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_seq', 1, false);


--
-- Name: disease_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.disease_seq', 1, false);


--
-- Name: division_sensor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.division_sensor_seq', 1, false);


--
-- Name: division_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.division_seq', 1, false);


--
-- Name: journal_entry_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.journal_entry_seq', 1, false);


--
-- Name: plant_sensor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plant_sensor_seq', 1, false);


--
-- Name: plant_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plant_seq', 1, false);


--
-- Name: plant_species_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plant_species_seq', 1, false);


--
-- Name: soil_quality_measurement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.soil_quality_measurement_seq', 1, false);


--
-- Name: species_family_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.species_family_seq', 1, false);


--
-- Name: task_settings_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_settings_seq', 1, false);


--
-- Name: tasks_current_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tasks_current_seq', 1, false);


--
-- Name: tasks_history_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tasks_history_seq', 1, false);


--
-- Name: utilizador_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilizador_seq', 1, false);


--
-- Name: air_quality_measurement air_quality_measurement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.air_quality_measurement
    ADD CONSTRAINT air_quality_measurement_pkey PRIMARY KEY (id);


--
-- Name: air_temperature_measurement air_temperature_measurement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.air_temperature_measurement
    ADD CONSTRAINT air_temperature_measurement_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: disease disease_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disease
    ADD CONSTRAINT disease_pkey PRIMARY KEY (id);


--
-- Name: disease_species disease_species_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disease_species
    ADD CONSTRAINT disease_species_pkey PRIMARY KEY (species_id, disease_id);


--
-- Name: division division_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.division
    ADD CONSTRAINT division_pkey PRIMARY KEY (id);


--
-- Name: division_sensor division_sensor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.division_sensor
    ADD CONSTRAINT division_sensor_pkey PRIMARY KEY (id);


--
-- Name: journal_entry journal_entry_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_entry
    ADD CONSTRAINT journal_entry_pkey PRIMARY KEY (id);


--
-- Name: plant plant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant
    ADD CONSTRAINT plant_pkey PRIMARY KEY (id);


--
-- Name: plant_sensor plant_sensor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant_sensor
    ADD CONSTRAINT plant_sensor_pkey PRIMARY KEY (id);


--
-- Name: plant_species plant_species_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant_species
    ADD CONSTRAINT plant_species_pkey PRIMARY KEY (id);


--
-- Name: reaction reaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reaction
    ADD CONSTRAINT reaction_pkey PRIMARY KEY (comment_id, user_id);


--
-- Name: soil_quality_measurement soil_quality_measurement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.soil_quality_measurement
    ADD CONSTRAINT soil_quality_measurement_pkey PRIMARY KEY (id);


--
-- Name: species_family species_family_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.species_family
    ADD CONSTRAINT species_family_pkey PRIMARY KEY (id);


--
-- Name: task_settings task_settings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_settings
    ADD CONSTRAINT task_settings_pkey PRIMARY KEY (id);


--
-- Name: tasks_current tasks_current_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks_current
    ADD CONSTRAINT tasks_current_pkey PRIMARY KEY (id);


--
-- Name: tasks_history tasks_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks_history
    ADD CONSTRAINT tasks_history_pkey PRIMARY KEY (id);


--
-- Name: utilizador uk_eougu510uft70icifeafv6cll; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilizador
    ADD CONSTRAINT uk_eougu510uft70icifeafv6cll UNIQUE (email);


--
-- Name: utilizador utilizador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilizador
    ADD CONSTRAINT utilizador_pkey PRIMARY KEY (id);


--
-- Name: air_temperature_measurement fk41u4nsy89voy1e9vs72l0ctii; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.air_temperature_measurement
    ADD CONSTRAINT fk41u4nsy89voy1e9vs72l0ctii FOREIGN KEY (sensor_id) REFERENCES public.division_sensor(id);


--
-- Name: tasks_history fk4mmjk9v9pmw39oa3ac192f1j6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks_history
    ADD CONSTRAINT fk4mmjk9v9pmw39oa3ac192f1j6 FOREIGN KEY (plant_id) REFERENCES public.plant(id);


--
-- Name: air_quality_measurement fk57swwrmull8tmmgwhw2w0s7ny; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.air_quality_measurement
    ADD CONSTRAINT fk57swwrmull8tmmgwhw2w0s7ny FOREIGN KEY (sensor_id) REFERENCES public.division_sensor(id);


--
-- Name: plant fk5jd6laslwrgxjwa3l5otl9jkr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant
    ADD CONSTRAINT fk5jd6laslwrgxjwa3l5otl9jkr FOREIGN KEY (species_id) REFERENCES public.plant_species(id);


--
-- Name: plant_species fk6n04l7vwbivxhn9q8ga1pa0ci; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant_species
    ADD CONSTRAINT fk6n04l7vwbivxhn9q8ga1pa0ci FOREIGN KEY (family_id) REFERENCES public.species_family(id);


--
-- Name: division fk6ppwaynkyagxqq91smwnsqidd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.division
    ADD CONSTRAINT fk6ppwaynkyagxqq91smwnsqidd FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- Name: soil_quality_measurement fk7bdgp1b6gbn8yn7guiw8iq3cb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.soil_quality_measurement
    ADD CONSTRAINT fk7bdgp1b6gbn8yn7guiw8iq3cb FOREIGN KEY (sensor_id) REFERENCES public.plant_sensor(id);


--
-- Name: disease_species fk87leowsufcg3mls3srwt5yjau; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disease_species
    ADD CONSTRAINT fk87leowsufcg3mls3srwt5yjau FOREIGN KEY (disease_id) REFERENCES public.disease(id);


--
-- Name: plant_sensor fk884kjcpa2h95xiseysvbxtdtv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant_sensor
    ADD CONSTRAINT fk884kjcpa2h95xiseysvbxtdtv FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- Name: disease_species fk8ajf2to31ynh49q8at0vuahr2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disease_species
    ADD CONSTRAINT fk8ajf2to31ynh49q8at0vuahr2 FOREIGN KEY (species_id) REFERENCES public.plant_species(id);


--
-- Name: comments fkb124pbetndh4k3bdkm6ffljgf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkb124pbetndh4k3bdkm6ffljgf FOREIGN KEY (plant_id) REFERENCES public.plant(id);


--
-- Name: plant_sensor fkbrhn62f4x2gttruutny9w1kl8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant_sensor
    ADD CONSTRAINT fkbrhn62f4x2gttruutny9w1kl8 FOREIGN KEY (plant_id) REFERENCES public.plant(id);


--
-- Name: comments fkcqig4x1jaw7fs00n87kre1p32; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkcqig4x1jaw7fs00n87kre1p32 FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- Name: division_sensor fkdaja3tfjeo4bqfais627qxe3u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.division_sensor
    ADD CONSTRAINT fkdaja3tfjeo4bqfais627qxe3u FOREIGN KEY (division_id) REFERENCES public.division(id);


--
-- Name: journal_entry fkgyalj060wprr46vnjhe3gi9v8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_entry
    ADD CONSTRAINT fkgyalj060wprr46vnjhe3gi9v8 FOREIGN KEY (plant_id) REFERENCES public.plant(id);


--
-- Name: journal_entry fkhjg29d4tlypqcirns3gd2w7jf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_entry
    ADD CONSTRAINT fkhjg29d4tlypqcirns3gd2w7jf FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- Name: reaction fkhsdvlyfl5xhtnkgv65ahshp2d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reaction
    ADD CONSTRAINT fkhsdvlyfl5xhtnkgv65ahshp2d FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- Name: plant fki3cr4h4gd0vuk7n6qxyycrda2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant
    ADD CONSTRAINT fki3cr4h4gd0vuk7n6qxyycrda2 FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- Name: reaction fkj142hj7ks4vy4dmno04j5kuue; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reaction
    ADD CONSTRAINT fkj142hj7ks4vy4dmno04j5kuue FOREIGN KEY (comment_id) REFERENCES public.comments(id);


--
-- Name: plant fkkfg428pcea4j36umlvtq913iv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plant
    ADD CONSTRAINT fkkfg428pcea4j36umlvtq913iv FOREIGN KEY (division_id) REFERENCES public.division(id);


--
-- Name: task_settings fkn3p6atn71ly33thnqst5ic1po; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task_settings
    ADD CONSTRAINT fkn3p6atn71ly33thnqst5ic1po FOREIGN KEY (plant_id) REFERENCES public.plant(id);


--
-- Name: tasks_current fkrr8o6mf2qjeqwtui7vo0ea0r9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks_current
    ADD CONSTRAINT fkrr8o6mf2qjeqwtui7vo0ea0r9 FOREIGN KEY (plant_id) REFERENCES public.plant(id);


--
-- Name: division_sensor fks07ef89lxgact4718geb85cr9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.division_sensor
    ADD CONSTRAINT fks07ef89lxgact4718geb85cr9 FOREIGN KEY (user_id) REFERENCES public.utilizador(id);


--
-- PostgreSQL database dump complete
--
