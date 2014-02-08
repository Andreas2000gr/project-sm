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
						 CREDIT_CARD_ID INTEGER(10), -- Προσθήκη αριθμού κάρτας ίδιος τύπος με το πεδίο του πίνακα καρτών
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
                      CUSTOMER INTEGER NOT NULL,
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

