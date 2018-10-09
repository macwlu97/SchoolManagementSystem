-- Generated by Oracle SQL Developer Data Modeler 18.1.0.082.1035
--   at:        2018-06-27 12:57:45 CEST
--   site:      Oracle Database 12c
--   type:      Oracle Database 12c



CREATE TABLE klasa (
    id_klasy                     NUMBER(20) NOT NULL,
    nazwa                        VARCHAR2(20 BYTE) NOT NULL,
    rok_szkolny                  VARCHAR2(20 BYTE) NOT NULL,
    nauczyciele_id_nauczyciela   NUMBER(10) NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX klasa_pk ON
    klasa (
        id_klasy
    ASC )
        LOGGING;

CREATE UNIQUE INDEX klasa__idx ON
    klasa (
        nauczyciele_id_nauczyciela
    ASC )
        LOGGING;

ALTER TABLE klasa ADD CONSTRAINT klasa_pk PRIMARY KEY ( id_klasy );

CREATE TABLE nauczyciele (
    id_nauczyciela   NUMBER(10) NOT NULL,
    imie             VARCHAR2(80 BYTE) NOT NULL,
    nazwisko         VARCHAR2(80 BYTE) NOT NULL,
    login            VARCHAR2(80 BYTE) NOT NULL,
    telefon          VARCHAR2(80 BYTE),
    email            VARCHAR2(80 BYTE),
    password         VARCHAR2(256 BYTE) NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX nauczyciele_pk ON
    nauczyciele (
        id_nauczyciela
    ASC )
        LOGGING;

ALTER TABLE nauczyciele ADD CONSTRAINT nauczyciele_pk PRIMARY KEY ( id_nauczyciela );

CREATE TABLE obecnosc (
    id_obecnosci   NUMBER(20) NOT NULL,
    uczniowie_id   NUMBER(10) NOT NULL,
    stan           NUMBER(20) NOT NULL,
    opis           VARCHAR2(80 BYTE),
    data           DATE,
    plan_id_zaj    NUMBER(20) NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX obecnosc_pk ON
    obecnosc (
        id_obecnosci
    ASC )
        LOGGING;

ALTER TABLE obecnosc ADD CONSTRAINT obecnosc_pk PRIMARY KEY ( id_obecnosci );

CREATE TABLE oceny (
    id_oceny                   NUMBER(20) NOT NULL,
    uczniowie_id               NUMBER(10) NOT NULL,
    przedmioty_id_przedmiotu   NUMBER(20) NOT NULL,
    ocena                      NUMBER(20) NOT NULL,
    opis                       VARCHAR2(200 BYTE),
    waga                       NUMBER(10)
)
LOGGING;

CREATE UNIQUE INDEX oceny_pk ON
    oceny (
        id_oceny
    ASC )
        LOGGING;

ALTER TABLE oceny ADD CONSTRAINT oceny_pk PRIMARY KEY ( id_oceny );

CREATE TABLE plan (
    id_zaj                     NUMBER(20) NOT NULL,
    przedmioty_id_przedmiotu   NUMBER(20) NOT NULL,
    godzina                    NUMBER(2) NOT NULL,
    klasa_id_klasy             NUMBER(20) NOT NULL,
    dzien                      NUMBER(2)
)
LOGGING;

CREATE UNIQUE INDEX plan_pk ON
    plan (
        id_zaj
    ASC )
        LOGGING;

ALTER TABLE plan ADD CONSTRAINT plan_pk PRIMARY KEY ( id_zaj );

CREATE TABLE przedmioty (
    id_przedmiotu                NUMBER(20) NOT NULL,
    nazwa                        VARCHAR2(80 BYTE) NOT NULL,
    nauczyciele_id_nauczyciela   NUMBER(10) NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX przedmioty_pk ON
    przedmioty (
        id_przedmiotu
    ASC )
        LOGGING;

ALTER TABLE przedmioty ADD CONSTRAINT przedmioty_pk PRIMARY KEY ( id_przedmiotu );

CREATE TABLE rodzice (
    id         NUMBER(10) NOT NULL,
    imie       VARCHAR2(55 BYTE),
    nazwisko   VARCHAR2(55 BYTE),
    password   VARCHAR2(256 BYTE),
    login      VARCHAR2(55 BYTE)
)
LOGGING;

CREATE UNIQUE INDEX rodzice_pk ON
    rodzice (
        id
    ASC )
        LOGGING;

ALTER TABLE rodzice ADD CONSTRAINT rodzice_pk PRIMARY KEY ( id );

CREATE TABLE rodziceuczniowie (
    rodzice_id     NUMBER(10) NOT NULL,
    uczniowie_id   NUMBER(10) NOT NULL
)
LOGGING;

ALTER TABLE rodziceuczniowie ADD CONSTRAINT rodziceuczniowie_pk PRIMARY KEY ( rodzice_id,
                                                                              uczniowie_id );

CREATE TABLE uczniowie (
    id               NUMBER(10) NOT NULL,
    imie             VARCHAR2(20 BYTE),
    nazwisko         VARCHAR2(20 BYTE),
    avatar           VARCHAR2(255 BYTE),
    data_urodzenia   DATE,
    login            VARCHAR2(55 BYTE),
    password         VARCHAR2(256 BYTE),
    klasa_id_klasy   NUMBER(20)
)
LOGGING;

CREATE UNIQUE INDEX uczniowie_pk ON
    uczniowie (
        id
    ASC )
        LOGGING;

ALTER TABLE uczniowie ADD CONSTRAINT uczniowie_pk PRIMARY KEY ( id );

CREATE TABLE uwagi (
    id_uwagi                     NUMBER(10) NOT NULL,
    uczniowie_id                 NUMBER(10) NOT NULL,
    opis                         VARCHAR2(200) NOT NULL,
    data                         DATE NOT NULL,
    nauczyciele_id_nauczyciela   NUMBER(10) NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX uwagi_pk ON
    uwagi (
        id_uwagi
    ASC )
        LOGGING;

ALTER TABLE uwagi ADD CONSTRAINT uwagi_pk PRIMARY KEY ( id_uwagi );

ALTER TABLE klasa
    ADD CONSTRAINT klasa_nauczyciele_fk FOREIGN KEY ( nauczyciele_id_nauczyciela )
        REFERENCES nauczyciele ( id_nauczyciela )
    NOT DEFERRABLE;

ALTER TABLE obecnosc
    ADD CONSTRAINT obecnosc_plan_fk FOREIGN KEY ( plan_id_zaj )
        REFERENCES plan ( id_zaj )
    NOT DEFERRABLE;

ALTER TABLE obecnosc
    ADD CONSTRAINT obecnosc_uczniowie_fk FOREIGN KEY ( uczniowie_id )
        REFERENCES uczniowie ( id )
    NOT DEFERRABLE;

ALTER TABLE oceny
    ADD CONSTRAINT oceny_przedmioty_fk FOREIGN KEY ( przedmioty_id_przedmiotu )
        REFERENCES przedmioty ( id_przedmiotu )
    NOT DEFERRABLE;

ALTER TABLE oceny
    ADD CONSTRAINT oceny_uczniowie_fk FOREIGN KEY ( uczniowie_id )
        REFERENCES uczniowie ( id )
    NOT DEFERRABLE;

ALTER TABLE plan
    ADD CONSTRAINT plan_klasa_fk FOREIGN KEY ( klasa_id_klasy )
        REFERENCES klasa ( id_klasy )
    NOT DEFERRABLE;

ALTER TABLE plan
    ADD CONSTRAINT plan_przedmioty_fk FOREIGN KEY ( przedmioty_id_przedmiotu )
        REFERENCES przedmioty ( id_przedmiotu )
    NOT DEFERRABLE;

ALTER TABLE przedmioty
    ADD CONSTRAINT przedmioty_nauczyciele_fk FOREIGN KEY ( nauczyciele_id_nauczyciela )
        REFERENCES nauczyciele ( id_nauczyciela )
    NOT DEFERRABLE;

ALTER TABLE rodziceuczniowie
    ADD CONSTRAINT rodziceuczniowie_rodzice_fk FOREIGN KEY ( rodzice_id )
        REFERENCES rodzice ( id )
    NOT DEFERRABLE;

ALTER TABLE rodziceuczniowie
    ADD CONSTRAINT rodziceuczniowie_uczniowie_fk FOREIGN KEY ( uczniowie_id )
        REFERENCES uczniowie ( id )
    NOT DEFERRABLE;

ALTER TABLE uczniowie
    ADD CONSTRAINT uczniowie_klasa_fk FOREIGN KEY ( klasa_id_klasy )
        REFERENCES klasa ( id_klasy )
    NOT DEFERRABLE;

ALTER TABLE uwagi
    ADD CONSTRAINT uwagi_nauczyciele_fk FOREIGN KEY ( nauczyciele_id_nauczyciela )
        REFERENCES nauczyciele ( id_nauczyciela )
    NOT DEFERRABLE;

ALTER TABLE uwagi
    ADD CONSTRAINT uwagi_uczniowie_fk FOREIGN KEY ( uczniowie_id )
        REFERENCES uczniowie ( id )
    NOT DEFERRABLE;



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                            10
-- ALTER TABLE                             23
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

	CREATE SEQUENCE  "G3_LIPINSKI97"."KLSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence OBSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."OBSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PLANSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."PLANSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PRZEDSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."PRZEDSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence RODZICSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."RODZICSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQCOMM
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."SEQCOMM"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQOCENY
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."SEQOCENY"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TEACHSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."TEACHSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TMPOBSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."TMPOBSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence UCZSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "G3_LIPINSKI97"."UCZSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

	
	CREATE OR REPLACE TRIGGER g3_lipinski97.COMM 
    BEFORE INSERT ON g3_lipinski97.uwagi 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_UWAGI" is null then 
         select SEQCOMM.nextval into :NEW."ID_UWAGI" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.OBTRIGGER 
    BEFORE INSERT ON g3_lipinski97.obecnosc 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_OBECNOSCI" is null then 
         select OBSEQ.nextval into :NEW."ID_OBECNOSCI" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.OCENKI 
    BEFORE INSERT ON g3_lipinski97.oceny 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_OCENY" is null then 
         select SEQOCENY.nextval into :NEW."ID_OCENY" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.PLANTRIGGER 
    BEFORE INSERT ON g3_lipinski97.plan 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_ZAJ" is null then 
         select PLANSEQ.nextval into :NEW."ID_ZAJ" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.PRZEDTRIGGER 
    BEFORE INSERT ON g3_lipinski97.przedmioty 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_PRZEDMIOTU" is null then 
         select PRZEDSEQ.nextval into :NEW."ID_PRZEDMIOTU" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.RODZICTRIGGER 
    BEFORE INSERT ON g3_lipinski97.rodzice 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select RODZICSEQ.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.TEACHTRIGGER 
    BEFORE INSERT ON g3_lipinski97.nauczyciele 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_NAUCZYCIELA" is null then 
         select TEACHSEQ.nextval into :NEW."ID_NAUCZYCIELA" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.TMPOBTRIGGER 
    BEFORE INSERT ON g3_lipinski97.tmp_obecnosci 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select TMPOBSEQ.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.TRIGGERKLASA 
    BEFORE INSERT ON g3_lipinski97.klasa 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID_KLASY" is null then 
         select KLSEQ.nextval into :NEW."ID_KLASY" from dual; 
      end if; 
   end if; 
end; 
/

CREATE OR REPLACE TRIGGER g3_lipinski97.UCZTRIGGER 
    BEFORE INSERT ON g3_lipinski97.uczniowie 
    FOR EACH ROW 
begin  
   if inserting then 
      if :NEW."ID" is null then 
         select UCZSEQ.nextval into :NEW."ID" from dual; 
      end if; 
   end if; 
end; 
/