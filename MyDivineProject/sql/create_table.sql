--DROP TABLE--

DROP TABLE "grade_book";
DROP TABLE "courses";
DROP TABLE "users";
DROP TABLE "topic";
DROP TABLE "roles";
DROP TABLE "states";



--CREATE TABLE ROLE--
CREATE TABLE "roles"(
                        id SERIAL  NOT NULL,
                        name CHARACTER VARYING(8) NOT NULL,
                        CONSTRAINT role_id PRIMARY KEY (id)
);

--CREATE TABLE TOPIC--
CREATE TABLE "topic" (
                         id SERIAL  NOT NULL ,
                         name CHARACTER VARYING(18) NOT NULL,
                         CONSTRAINT topic_id PRIMARY KEY (id)
);

--CREATE TABLE STATES--
create TABLE "states"(
    id SERIAL not null ,
    name CHARACTER varying(12) not null ,
    CONSTRAINT states_id PRIMARY KEY (id)
);

--CREATE TABLE USER--
CREATE TABLE "users"(
    login VARCHAR(16) PRIMARY KEY NOT NULL,
    password VARCHAR(32) NOT NULL ,
    fullName VARCHAR(32) NOT NULL ,
    role_id INTEGER  DEFAULT 1,
    blocked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (role_id) REFERENCES "roles" (id)
        ON UPDATE NO ACTION ON DELETE CASCADE
);

--CREATE TABLE COURSE--
CREATE TABLE "courses"(
    id SERIAL primary key NOT NULL ,
    name VARCHAR(32) NOT NULL ,
    topic_id INTEGER not null ,
    state_id INTEGER NOT NULL DEFAULT 1,
    teacher  VARCHAR(32) REFERENCES "users"(login)
    ON UPDATE NO ACTION ON DELETE CASCADE,
    start_date DATE not null,
    end_date DATE NOT NULL ,
    count INTEGER,
    FOREIGN KEY (topic_id) REFERENCES "topic"(id)
        ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (state_id) REFERENCES "states"(id)
        ON UPDATE NO ACTION ON DELETE CASCADE


);



--CREATE TABLE COURSE_USERS--
CREATE TABLE "grade_book"(
    id SERIAL NOT NULL primary key ,
    user_login VARCHAR(32) NOT NULL ,
    course_id  INTEGER  NOT NULL ,
    result boolean default false,
    assessment integer,
    FOREIGN KEY (user_login) REFERENCES "users"(login)
        ON UPDATE NO ACTION ON DELETE CASCADE,
     FOREIGN KEY (course_id) REFERENCES "courses"(id)
        ON UPDATE NO ACTION ON DELETE CASCADE


)