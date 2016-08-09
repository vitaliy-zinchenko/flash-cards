--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE card (
    id bigint NOT NULL,
    translation character varying(255),
    word character varying(255),
    card_set_id bigint
);


ALTER TABLE card OWNER TO postgres;

--
-- Name: card_set; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE card_set (
    id bigint NOT NULL,
    title character varying(255),
    language_id bigint,
    translation_language_id bigint
);


ALTER TABLE card_set OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: language; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE language (
    id bigint NOT NULL,
    title character varying(255)
);


ALTER TABLE language OWNER TO postgres;

--
-- Data for Name: card; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY card (id, translation, word, card_set_id) FROM stdin;
1	t1	w1	1
2	t2	w2	1
\.


--
-- Data for Name: card_set; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY card_set (id, title, language_id, translation_language_id) FROM stdin;
1	test1	1	2
2	test2	2	1
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY language (id, title) FROM stdin;
1	English
2	Russian
\.


--
-- Name: card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card
    ADD CONSTRAINT card_pkey PRIMARY KEY (id);


--
-- Name: card_set_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_set
    ADD CONSTRAINT card_set_pkey PRIMARY KEY (id);


--
-- Name: language_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY language
    ADD CONSTRAINT language_pkey PRIMARY KEY (id);


--
-- Name: fk7qha0kl4v6eawyjd0mlgwiroc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card
    ADD CONSTRAINT fk7qha0kl4v6eawyjd0mlgwiroc FOREIGN KEY (card_set_id) REFERENCES card_set(id);


--
-- Name: fkagdmompth2ofd20qqgy8pj9im; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_set
    ADD CONSTRAINT fkagdmompth2ofd20qqgy8pj9im FOREIGN KEY (translation_language_id) REFERENCES language(id);


--
-- Name: fkh8i5tvppgqx7j1j0r8ur8spkw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_set
    ADD CONSTRAINT fkh8i5tvppgqx7j1j0r8ur8spkw FOREIGN KEY (language_id) REFERENCES language(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

