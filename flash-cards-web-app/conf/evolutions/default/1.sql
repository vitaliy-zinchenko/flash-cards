# User schema

# --- !Ups
create table users (
    id bigserial NOT NULL,
    first_name character varying,
    last_name character varying,
    email character varying,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

create table card_set (
    id bigserial NOT NULL,
    title character varying,
    user_id bigint,
    CONSTRAINT card_set_pk PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE card (
    id bigserial NOT NULL,
    word character varying(255),
    translation character varying(255),
    card_set_id bigint,
    CONSTRAINT card_pk PRIMARY KEY (id),
    FOREIGN KEY (card_set_id) REFERENCES card_set(id) ON DELETE CASCADE
);

