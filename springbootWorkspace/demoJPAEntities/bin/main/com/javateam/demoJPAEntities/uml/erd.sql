
/* Drop Tables */

DROP TABLE JSP.CATEGORY_ITEM CASCADE CONSTRAINTS;
DROP TABLE JSP.CATEGORY CASCADE CONSTRAINTS;
DROP TABLE JSP.ORDER_ITEM CASCADE CONSTRAINTS;
DROP TABLE JSP.ORDERS CASCADE CONSTRAINTS;
DROP TABLE JSP.DELIVERY CASCADE CONSTRAINTS;
DROP TABLE JSP.ITEM CASCADE CONSTRAINTS;
DROP TABLE JSP.MEMBER CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE JSP.DEMO_SEQ;
DROP SEQUENCE JSP.DEMO_VO_SEQ;
DROP SEQUENCE JSP.HIBERNATE_SEQUENCE;
DROP SEQUENCE JSP.TEST_SEQ;




/* Create Sequences */

CREATE SEQUENCE JSP.DEMO_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 99999 START WITH 461 CACHE 20;
CREATE SEQUENCE JSP.DEMO_VO_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 41 CACHE 20;
CREATE SEQUENCE JSP.HIBERNATE_SEQUENCE INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 CACHE 20;
CREATE SEQUENCE JSP.TEST_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 CACHE 20;



/* Create Tables */

CREATE TABLE JSP.CATEGORY
(
	CATEGORY_ID number(4,0) NOT NULL,
	NAME varchar2(255),
	PARENT_ID number(4,0),
	CONSTRAINT SYS_C008690 PRIMARY KEY (CATEGORY_ID)
);


CREATE TABLE JSP.CATEGORY_ITEM
(
	CATEGORY_ID number(4,0) NOT NULL,
	ITEM_ID number(4,0) NOT NULL
);


CREATE TABLE JSP.DELIVERY
(
	DELIVERY_ID number(4,0) NOT NULL,
	CITY varchar2(255),
	STREET varchar2(255),
	ZIPCODE varchar2(255),
	STATUS varchar2(255),
	CONSTRAINT SYS_C008694 PRIMARY KEY (DELIVERY_ID)
);


CREATE TABLE JSP.ITEM
(
	DTYPE varchar2(31) NOT NULL,
	ITEM_ID number(4,0) NOT NULL,
	NAME varchar2(255),
	PRICE number(10,0) NOT NULL,
	STOCK_QUANTITY number(10,0) NOT NULL,
	ARTIST varchar2(255),
	ETC varchar2(255),
	ACTOR varchar2(255),
	DIRECTOR varchar2(255),
	AUTHOR varchar2(255),
	ISBN varchar2(255),
	CONSTRAINT SYS_C008699 PRIMARY KEY (ITEM_ID)
);


CREATE TABLE JSP.MEMBER
(
	MEMBER_ID number(4,0) NOT NULL,
	CITY varchar2(255),
	STREET varchar2(255),
	ZIPCODE varchar2(255),
	NAME varchar2(255),
	CONSTRAINT SYS_C008701 PRIMARY KEY (MEMBER_ID)
);


CREATE TABLE JSP.ORDERS
(
	ORDER_ID number(4,0) NOT NULL,
	ORDER_DATE timestamp,
	STATUS varchar2(255),
	DELIVERY_ID number(4,0),
	MEMBER_ID number(4,0),
	CONSTRAINT SYS_C008707 PRIMARY KEY (ORDER_ID)
);


CREATE TABLE JSP.ORDER_ITEM
(
	ORDER_ITEM_ID number(4,0) NOT NULL,
	COUNT number(10,0) NOT NULL,
	ORDER_PRICE number(10,0) NOT NULL,
	ITEM_ID number(4,0),
	ORDER_ID number(4,0),
	CONSTRAINT SYS_C008705 PRIMARY KEY (ORDER_ITEM_ID)
);



/* Create Foreign Keys */

ALTER TABLE JSP.CATEGORY
	ADD CONSTRAINT FK2Y94SVPMQTTX80MSHYNY85WQR FOREIGN KEY (PARENT_ID)
	REFERENCES JSP.CATEGORY (CATEGORY_ID)
;


ALTER TABLE JSP.CATEGORY_ITEM
	ADD CONSTRAINT FKCQ2N0OPF5SHYH84EX1FHUKCBH FOREIGN KEY (CATEGORY_ID)
	REFERENCES JSP.CATEGORY (CATEGORY_ID)
;


ALTER TABLE JSP.ORDERS
	ADD CONSTRAINT FKTKRUR7WG4D8AX0PWGO0VMY20C FOREIGN KEY (DELIVERY_ID)
	REFERENCES JSP.DELIVERY (DELIVERY_ID)
;


ALTER TABLE JSP.CATEGORY_ITEM
	ADD CONSTRAINT FKU8B4LWQUTCDQ3363GF6MLUJQ FOREIGN KEY (ITEM_ID)
	REFERENCES JSP.ITEM (ITEM_ID)
;


ALTER TABLE JSP.ORDER_ITEM
	ADD CONSTRAINT FKIJA6HJJIIT8DPRNMVTVGDP6RU FOREIGN KEY (ITEM_ID)
	REFERENCES JSP.ITEM (ITEM_ID)
;


ALTER TABLE JSP.ORDERS
	ADD CONSTRAINT FKPKTXWHJ3X9M4GTH5FF6BKQGEB FOREIGN KEY (MEMBER_ID)
	REFERENCES JSP.MEMBER (MEMBER_ID)
;


ALTER TABLE JSP.ORDER_ITEM
	ADD CONSTRAINT FKT4DC2R9NBVBUJRLJV3E23IIBT FOREIGN KEY (ORDER_ID)
	REFERENCES JSP.ORDERS (ORDER_ID)
;


