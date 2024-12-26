ALTER TABLE product
    ADD quantity INT NULL;

ALTER TABLE product
    MODIFY quantity INT NOT NULL;

ALTER TABLE product
DROP
COLUMN qty;

ALTER TABLE st_users
    MODIFY avgrating DOUBLE NOT NULL;

ALTER TABLE st_users
    MODIFY mentor_rating DOUBLE NOT NULL;

ALTER TABLE st_users
    MODIFY number_of_sessions INT NOT NULL;

ALTER TABLE st_users
    MODIFY user_type INT NULL;