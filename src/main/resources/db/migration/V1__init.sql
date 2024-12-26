CREATE TABLE category
(
    id          BIGINT NOT NULL,
    created_at  datetime NULL,
    modified_at datetime NULL,
    title       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id            BIGINT NOT NULL,
    number_of_sessions INT    NOT NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id BIGINT NOT NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_ta
(
    user_id            BIGINT NOT NULL,
    number_of_sessions INT    NOT NULL,
    avgrating DOUBLE NOT NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (user_id)
);

CREATE TABLE jt_users
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_users PRIMARY KEY (id)
);

CREATE TABLE mp_instructor
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    CONSTRAINT pk_mp_instructor PRIMARY KEY (id)
);

CREATE TABLE mp_mentor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_mp_mentor PRIMARY KEY (id)
);

CREATE TABLE mp_ta
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    avgrating DOUBLE NOT NULL,
    CONSTRAINT pk_mp_ta PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT NOT NULL,
    created_at    datetime NULL,
    modified_at   datetime NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    image         VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_users
(
    id                 BIGINT NOT NULL,
    user_type          INT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    avgrating DOUBLE NOT NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_st_users PRIMARY KEY (id)
);

CREATE TABLE tp_instructor
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    CONSTRAINT pk_tp_instructor PRIMARY KEY (id)
);

CREATE TABLE tp_mentor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    mentor_rating DOUBLE NOT NULL,
    CONSTRAINT pk_tp_mentor PRIMARY KEY (id)
);

CREATE TABLE tp_ta
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    number_of_sessions INT    NOT NULL,
    avgrating DOUBLE NOT NULL,
    CONSTRAINT pk_tp_ta PRIMARY KEY (id)
);

CREATE TABLE tp_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_tp_user PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_users (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_users (id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_USER FOREIGN KEY (user_id) REFERENCES jt_users (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);