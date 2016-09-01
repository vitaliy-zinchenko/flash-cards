--
-- Name: answer_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE answer_type (
    id bigint NOT NULL,
    code character varying(255),
    title character varying(255)
);


--ALTER TABLE answer_type OWNER TO postgres;

--
-- Name: card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE card (
    id bigint NOT NULL,
    translation character varying(255),
    word character varying(255),
    card_set_id bigint
);


--ALTER TABLE card OWNER TO postgres;

--
-- Name: card_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE card_answer (
    id bigint NOT NULL,
    correct boolean,
    "time" bytea,
    answer_type_id bigint,
    card_id bigint
);


--ALTER TABLE card_answer OWNER TO postgres;

--
-- Name: card_set; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE card_set (
    id bigint NOT NULL,
    title character varying(255),
    language_id bigint,
    translation_language_id bigint,
    user_id bigint
);


--ALTER TABLE card_set OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: language; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE language (
    id bigint NOT NULL,
    title character varying(255)
);


--ALTER TABLE language OWNER TO postgres;

--
-- Name: user_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_info (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255)
);


--ALTER TABLE user_info OWNER TO postgres;


--
-- Name: answer_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY answer_type
    ADD CONSTRAINT answer_type_pkey PRIMARY KEY (id);


--
-- Name: card_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_answer
    ADD CONSTRAINT card_answer_pkey PRIMARY KEY (id);


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
-- Name: ukgnu0k8vv6ptioedbxbfsnan9g; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT ukgnu0k8vv6ptioedbxbfsnan9g UNIQUE (email);


--
-- Name: user_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_info
    ADD CONSTRAINT user_info_pkey PRIMARY KEY (id);


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
-- Name: fkb6dgt7lux43e3yvaptxeecte8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_answer
    ADD CONSTRAINT fkb6dgt7lux43e3yvaptxeecte8 FOREIGN KEY (card_id) REFERENCES card(id);


--
-- Name: fkgk2eo4xu1aa60377g5riqls9r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_answer
    ADD CONSTRAINT fkgk2eo4xu1aa60377g5riqls9r FOREIGN KEY (answer_type_id) REFERENCES answer_type(id);


--
-- Name: fkh8i5tvppgqx7j1j0r8ur8spkw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_set
    ADD CONSTRAINT fkh8i5tvppgqx7j1j0r8ur8spkw FOREIGN KEY (language_id) REFERENCES language(id);


--
-- Name: fkn88tw44it3ytx59q3gww34xo7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY card_set
    ADD CONSTRAINT fkn88tw44it3ytx59q3gww34xo7 FOREIGN KEY (user_id) REFERENCES user_info(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

--REVOKE ALL ON SCHEMA public FROM PUBLIC;
--REVOKE ALL ON SCHEMA public FROM postgres;
--GRANT ALL ON SCHEMA public TO postgres;
--GRANT ALL ON SCHEMA public TO PUBLIC;
