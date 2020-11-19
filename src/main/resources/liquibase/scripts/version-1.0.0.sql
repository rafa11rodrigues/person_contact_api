--liquibase formatted sql

--changeset rafael_santos:DDL_1
CREATE TABLE PERSON (
    ID UUID CONSTRAINT PK_PERSON PRIMARY KEY,
    NAME VARCHAR(80) NOT NULL,
    CPF VARCHAR(11) NOT NULL CONSTRAINT UK_PERSON_CPF UNIQUE,
    BIRTH_DATE DATE NOT NULL
);

--changeset rafael_santos:DDL_2
CREATE TABLE CONTACT (
    ID UUID CONSTRAINT PK_CONTACT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PHONE VARCHAR(20) NOT NULL,
    PERSON_ID UUID NOT NULL,

    CONSTRAINT FK_CONTACT_PERSON FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID)
);
CREATE INDEX IDX_CONTACT_PERSON
ON CONTACT(PERSON_ID);