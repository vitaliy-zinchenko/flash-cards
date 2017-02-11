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
    FOREIGN KEY (card_set_id) REFERENCES card_set(id)
);

insert into users (id, first_name, last_name, email) values (1, 'test', 'test', 'zinjvi@gmail.com');

--insert into card_set (id, title, user_id) values (1, 'test', 1);
--insert into card_set (id, title, user_id) values (2, 'test2', 1);
--insert into card_set (id, title, user_id) values (3, 'test3', 1);
--insert into card_set (id, title, user_id) values (4, 'test4', 1);
--insert into card_set (id, title, user_id) values (5, 'test5', 1);

--insert into card (id, word, translation, card_set_id) values (1, 'w1', 't1', '1');
--insert into card (id, word, translation, card_set_id) values (2, 'w2', 't2', '1');
--insert into card (id, word, translation, card_set_id) values (3, 'w3', 't3', '1');
--insert into card (id, word, translation, card_set_id) values (4, 'w4', 't4', '1');
--insert into card (id, word, translation, card_set_id) values (5, 'w5', 't5', '1');
--insert into card (id, word, translation, card_set_id) values (10, 'w10', 't10', 2);
--
--
--
--insert into card (word, translation, card_set_id) values ('w10', 't10', 2);
--
--drop table users;
--drop table card_set;
--drop table card;
