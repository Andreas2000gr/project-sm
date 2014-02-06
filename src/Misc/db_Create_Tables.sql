CREATE SEQUENCE seq_Check_Card;
CREATE SEQUENCE seq_Credit_Card;
CREATE SEQUENCE seq_Bank;
CREATE SEQUENCE seq_Customer;
CREATE SEQUENCE seq_Product_Purchase;
CREATE SEQUENCE seq_Purchase;
CREATE SEQUENCE seq_Store;
CREATE SEQUENCE seq_Product;
CREATE TABLE Check_Card (
  check_id    integer NOT NULL, 
  check_code  varchar(50) NOT NULL, 
  active      boolean NOT NULL, 
  customer_id integer NOT NULL, 
  create_date date NOT NULL, 
  PRIMARY KEY (check_id));
CREATE TABLE Credit_Card (
  card_id     integer NOT NULL, 
  card_number varchar(100) NOT NULL, 
  owner_name  varchar(100) NOT NULL, 
  cvv         integer NOT NULL, 
  bank_id     integer NOT NULL, 
  PRIMARY KEY (card_id));
CREATE TABLE Bank (
  bank_id   integer NOT NULL, 
  bank_name varchar(50), 
  PRIMARY KEY (bank_id));
CREATE TABLE Customer (
  customer_id        integer NOT NULL, 
  first_name         varchar(30) NOT NULL, 
  last_name          varchar(40) NOT NULL, 
  address            varchar(50), 
  points_card_number varchar(20) NOT NULL, 
  available_points   integer NOT NULL, 
  noOfChecks         integer, 
  password           varchar(50) NOT NULL, 
  card_id            integer NOT NULL, 
  PRIMARY KEY (customer_id));
CREATE TABLE Product_Purchase (
  product_purchase_id integer NOT NULL, 
  product             integer NOT NULL, 
  purchase            integer NOT NULL, 
  quantity            integer NOT NULL, 
  PRIMARY KEY (product_purchase_id));
CREATE TABLE Purchase (
  purchase_id   integer NOT NULL, 
  datetime      timestamp NOT NULL, 
  amount        float(10) NOT NULL, 
  points_earned integer NOT NULL, 
  delivery      integer NOT NULL, 
  store         integer NOT NULL, 
  customer      integer NOT NULL, 
  PRIMARY KEY (purchase_id));
CREATE TABLE Store_Product (
  Productproduct_id integer NOT NULL, 
  Storestore_id     integer NOT NULL, 
  PRIMARY KEY (Productproduct_id, 
  Storestore_id));
CREATE TABLE Store (
  store_id integer NOT NULL, 
  name     varchar(50), 
  address  varchar(200), 
  PRIMARY KEY (store_id));
CREATE TABLE Product (
  product_id integer NOT NULL, 
  name       varchar(30) NOT NULL, 
  code       varchar(10) NOT NULL, 
  points     integer NOT NULL, 
  price      float(10) NOT NULL, 
  PRIMARY KEY (product_id));
ALTER TABLE Check_Card ADD CONSTRAINT FKCheck_Card628570 FOREIGN KEY (customer_id) REFERENCES Customer (customer_id);
ALTER TABLE Customer ADD CONSTRAINT FKCustomer158809 FOREIGN KEY (card_id) REFERENCES Credit_Card (card_id);
ALTER TABLE Credit_Card ADD CONSTRAINT FKCredit_Car75489 FOREIGN KEY (bank_id) REFERENCES Bank (bank_id);
ALTER TABLE Purchase ADD CONSTRAINT make FOREIGN KEY (customer) REFERENCES Customer (customer_id);
ALTER TABLE Product_Purchase ADD CONSTRAINT FKProduct_Pu186007 FOREIGN KEY (purchase) REFERENCES Purchase (purchase_id);
ALTER TABLE Product_Purchase ADD CONSTRAINT FKProduct_Pu946867 FOREIGN KEY (product) REFERENCES Product (product_id);
ALTER TABLE Purchase ADD CONSTRAINT performs FOREIGN KEY (store) REFERENCES Store (store_id);
ALTER TABLE Store_Product ADD CONSTRAINT FKStore_Prod98708 FOREIGN KEY (Storestore_id) REFERENCES Store (store_id);
ALTER TABLE Store_Product ADD CONSTRAINT FKStore_Prod912213 FOREIGN KEY (Productproduct_id) REFERENCES Product (product_id);
CREATE UNIQUE INDEX Check_Card_check_id 
  ON Check_Card (check_id);
CREATE INDEX Check_Card_customer_id 
  ON Check_Card (customer_id);
CREATE UNIQUE INDEX Credit_Card_card_id 
  ON Credit_Card (card_id);
CREATE INDEX Credit_Card_bank_id 
  ON Credit_Card (bank_id);
CREATE UNIQUE INDEX Bank_bank_id 
  ON Bank (bank_id);
CREATE UNIQUE INDEX Customer_customer_id 
  ON Customer (customer_id);
CREATE INDEX Customer_card_id 
  ON Customer (card_id);
CREATE UNIQUE INDEX Product_Purchase_product_purchase_id 
  ON Product_Purchase (product_purchase_id);
CREATE UNIQUE INDEX Purchase_purchase_id 
  ON Purchase (purchase_id);
CREATE UNIQUE INDEX Store_store_id 
  ON Store (store_id);
CREATE UNIQUE INDEX Product_product_id 
  ON Product (product_id);
