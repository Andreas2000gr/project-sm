/*
Η βάση πρέπει να ονομαστεί SM με χρήστη και password sm ώστε να ταιριάζει κατευθείαν με
τις ρυθμίσεις του persistence.xml χωρίς αλλαγές. 
*/
--------------------------------------------------------------------------------------------------------------
/*DDL FOR PRODUCT TABLE */
--------------------------------------------------------------------------------------------------------------
CREATE TABLE "PRODUCT" (
                        PRODUCT_ID INTEGER NOT NULL,
                        NAME VARCHAR(30) NOT NULL,
                        CODE VARCHAR(10) NOT NULL,
                        POINTS INTEGER NOT NULL,
                        PRICE FLOAT(10) NOT NULL
                        );
--------------------------------------------------------------------------------------------------------------
/*DDL FOR STORE TABLE   */
--------------------------------------------------------------------------------------------------------------

CREATE TABLE "STORE" (
                      STORE_ID INTEGER NOT NULL,
                      NAME VARCHAR(50) NOT NULL,
                      ADDRESS VARCHAR(200) NOT NULL
                      );
--------------------------------------------------------------------------------------------------------------
/*DDL FOR STORE_PRODUCT TABLE   */
--------------------------------------------------------------------------------------------------------------                     
CREATE TABLE "STORE_PRODUCT" (
                              PRODUCT INTEGER NOT NULL,
                              STORE INTEGER NOT NULL
                              );

--------------------------------------------------------------------------------------------------------------
/*DDL FOR CUSTOMER TABLE   */
--------------------------------------------------------------------------------------------------------------
CREATE TABLE "CUSTOMER" (
                         CUSTOMER_ID INTEGER NOT NULL,
                         FIRST_NAME VARCHAR(30) NOT NULL,
                         LAST_NAME VARCHAR(40) NOT NULL,
                         ADDRESS VARCHAR(50),
                         POINTS_CARD_NUMBER VARCHAR(20) NOT NULL, -- Το οποίο έγινε και UN
			 CREDIT_CARD_ID INTEGER, -- Προσθήκη αριθμού κάρτας ίδιος τύπος με το πεδίο του πίνακα καρτών
                         AVAILABLE_POINTS INTEGER  NOT NULL,
                         --NO_OF_CHECKS INTEGER Ακυρώθηκε στην νέα έκδοση της εφαρμογής
			 PASSWORD VARCHAR(8) -- Προσθήκη Password
                         );
--------------------------------------------------------------------------------------------------------------
/*DDL FOR PRODUCT_PURCHASE TABLE  */
--------------------------------------------------------------------------------------------------------------
CREATE TABLE "PRODUCT_PURCHASE"(
                                PRODUCT_PURCHASE_ID INTEGER NOT NULL,
                                PRODUCT_ID INTEGER NOT NULL,
                                PURCHASE_ID INTEGER NOT NULL,
                                QUANTITY INTEGER NOT NULL
                                );
--------------------------------------------------------------------------------------------------------------
/* DDL FOR PURCHASE TABLE  */
--------------------------------------------------------------------------------------------------------------
CREATE TABLE "PURCHASE" (
                         PURCHASE_ID INTEGER NOT NULL,
                         DATETIME TIMESTAMP NOT NULL,
                         AMOUNT FLOAT(10) NOT NULL,
                         POINTS_EARNED INTEGER NOT NULL,
                         DELIVERY BOOLEAN NOT NULL,
                         CUSTOMER INTEGER NOT NULL,
                         STORE INTEGER NOT NULL
                         );

--------------------------------------------------------------------------------------------------------------
/* DDL FOR VOUCHER TABLE  */
--------------------------------------------------------------------------------------------------------------
CREATE TABLE "VOUCHER" (
                      VOUCHER_ID INTEGER NOT NULL,
                      VOUCHER_STATUS VARCHAR(1) NOT NULL,
                      CUSTOMER INTEGER NOT NULL
                      );
						 
--------------------------------------------------------------------------------------------------------------
/* DDL FOR PRIMARY KEYS */
--------------------------------------------------------------------------------------------------------------

ALTER TABLE PRODUCT ADD CONSTRAINT PK_PRODUCT_ID PRIMARY KEY (PRODUCT_ID) ;
ALTER TABLE STORE ADD CONSTRAINT PK_STORE_ID PRIMARY KEY (STORE_ID) ;
ALTER TABLE STORE_PRODUCT ADD CONSTRAINT PK_STORE_PRODUCT_ID PRIMARY KEY (STORE,PRODUCT) ;
ALTER TABLE CUSTOMER ADD CONSTRAINT PK_CUSTOMER_ID PRIMARY KEY (CUSTOMER_ID) ;
ALTER TABLE PRODUCT_PURCHASE ADD CONSTRAINT PK_PRODUCT_PURCHASE_ID PRIMARY KEY (PRODUCT_PURCHASE_ID) ;
ALTER TABLE PURCHASE ADD CONSTRAINT PK_PURCHASE_ID PRIMARY KEY (PURCHASE_ID) ;
ALTER TABLE VOUCHER ADD CONSTRAINT PK_VOUCHER_ID PRIMARY KEY (VOUCHER_ID) ;

--------------------------------------------------------------------------------------------------------------
/* DDL FOR FOREIGN KEYS */
--------------------------------------------------------------------------------------------------------------
ALTER TABLE STORE_PRODUCT ADD CONSTRAINT FK_STORE_ID FOREIGN KEY(STORE) REFERENCES STORE(STORE_ID);
ALTER TABLE STORE_PRODUCT ADD CONSTRAINT FK_PRODUCT_ID FOREIGN KEY(PRODUCT) REFERENCES PRODUCT(PRODUCT_ID);
ALTER TABLE PRODUCT_PURCHASE ADD CONSTRAINT FK_PPUR_PRODUCT_ID FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID);
ALTER TABLE PRODUCT_PURCHASE ADD CONSTRAINT FK_PPUR_PURCHASE_ID FOREIGN KEY(PURCHASE_ID) REFERENCES PURCHASE(PURCHASE_ID);
ALTER TABLE PURCHASE ADD CONSTRAINT FK_PUR_CUSTOMER_ID FOREIGN KEY(CUSTOMER) REFERENCES CUSTOMER(CUSTOMER_ID);
ALTER TABLE PURCHASE ADD CONSTRAINT FK_PUR_STORE_ID FOREIGN KEY(STORE) REFERENCES STORE(STORE_ID);
ALTER TABLE VOUCHER ADD CONSTRAINT FK_VOUC_CUSTOMER_ID FOREIGN KEY(CUSTOMER) REFERENCES CUSTOMER(CUSTOMER_ID);

--------------------------------------------------------------------------------------------------------------
/* DDL FOR PRIMARY-KEY SEQUENCES */
--------------------------------------------------------------------------------------------------------------
CREATE SEQUENCE SQ_CUSTOMER_ID MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_PRODUCT_ID MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_PRODUCT_PURCHASE_ID MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_PURCHASE_ID MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1; 
CREATE SEQUENCE SQ_STORE_ID MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_VOUCHER_ID MINVALUE 1 MAXVALUE 99999999 START WITH 1 INCREMENT BY 1;

--------------------------------------------------------------------------------------------------------------
/* DML FOR DB POPULATION */
--------------------------------------------------------------------------------------------------------------

-- STORE
INSERT INTO SM.STORE (STORE_ID, "NAME", ADDRESS) 
	VALUES (1, 'Βερόπουλος Αθηνών', 'Λ. Πατησίων 234');
INSERT INTO SM.STORE (STORE_ID, "NAME", ADDRESS) 
	VALUES (2, 'Βερόπουλος Αμαρουσίου', 'Λ. Κηφισίας 189');
INSERT INTO SM.STORE (STORE_ID, "NAME", ADDRESS) 
	VALUES (3, 'Βερόπουλος Γέρακα', 'Αγ. Κωνσταντίνου 29');


-- PRODUCTS
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (1,'Aim Οδοντόκρεμα 75 ml', '1010', 15, 4.23);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (2,'Μακαρόνια No7', '2020', 10, 0.75);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (3,'Αλεύρι Ολικής', '1020', 10, 10.3);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (4,'Λάδι 5lt', '1030', 50, 21.45);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (5,'Δημητριακά ολικής', '1040', 20, 5.25);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (6,'Χαρτί κουζίνας', '1050', 10, 5.40);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (7,'Μακαρόνια No3', '1060', 15, 1.25);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (8,'Badedas Αφρόλουτρο 750 ml', '1070', 5, 6.44);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (9,'Καφές 250gr', '2010', 15, 3.2);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (10,'Απορρυπαντικό Ρούχων', '1080', 50, 26.5);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (11,'Maggi Κύβοι Ζωμό 12τεμ.', '1090', 20, 5.5);
INSERT INTO SM.PRODUCT (PRODUCT_ID, "NAME", CODE, POINTS, PRICE) 
	VALUES (12,'Μουστάρδα 500 gr', '2000', 10, 3.66);


-- CUSTOMERS
INSERT INTO SM.CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, ADDRESS, POINTS_CARD_NUMBER, CREDIT_CARD_ID, AVAILABLE_POINTS, PASSWORD) 
	VALUES (1, 'Andreas', 'Paradise', 'Αιόλου 29, Γαλάτσι', '1010-1010', 1, 0, 'aparadis');
INSERT INTO SM.CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, ADDRESS, POINTS_CARD_NUMBER, CREDIT_CARD_ID, AVAILABLE_POINTS, PASSWORD) 
	VALUES (2, 'Panagis', 'Loukatos', 'Αγ. Μαρίνας 33, Πειραιάς', '2020-2020', 2, 0, 'ploukato');
INSERT INTO SM.CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, ADDRESS, POINTS_CARD_NUMBER, CREDIT_CARD_ID, AVAILABLE_POINTS, PASSWORD) 
	VALUES (3, 'Anestis', 'Passas', 'Δωδεκανήσου 20, Χολαργός', '3030-3030', 3, 0, 'apassas');
INSERT INTO SM.CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, ADDRESS, POINTS_CARD_NUMBER, CREDIT_CARD_ID, AVAILABLE_POINTS, PASSWORD) 
	VALUES (4, 'Evangelia', 'Papavasileiou', 'Αγ. Διονυσίου 123, Αγ. Παρασκευή', '4040-4040', 4, 0, 'epapavas');


-- STORE_PRODUCT
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (1 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (2 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (3 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (4 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (5 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (6 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (7 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (8 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (9 , 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (10, 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (11, 1);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (12, 1);


INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (1 , 2);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (3 , 2);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (6 , 2);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (7 , 2);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (8 , 2);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (11, 2);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (12, 2);


INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (1 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (2 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (3 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (4 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (5 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (6 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (7 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (8 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (9 , 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (10, 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (11, 3);
INSERT INTO SM.STORE_PRODUCT (PRODUCT, STORE) VALUES (12, 3);
