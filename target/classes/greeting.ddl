CREATE TABLE GREETING (
    SALUTATION VARCHAR(64),
    RESPONSE VARCHAR(64),
    constraint pk_salutation primary key(salutation)
);

INSERT INTO GREETING (SALUTATION, RESPONSE) VALUES ('Marco', 'Polo');