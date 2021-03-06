CREATE TABLE member_tbl (
  member_id VARCHAR2(20) PRIMARY KEY,
  member_nickname VARCHAR2(50) NOT NULL,
  member_name VARCHAR2(50) NOT NULL,
  member_gender CHAR(1) NOT NULL,
  member_email VARCHAR2(50) NOT NULL UNIQUE,
  member_phone VARCHAR2(13) NOT NULL UNIQUE,
  member_birth DATE,
  member_zip VARCHAR2(5),
  member_address VARCHAR2(300),
  member_joindate DATE DEFAULT sysdate
);
 
DROP TABLE member_tbl;
 
CREATE TABLE member_tbl (
  member_id VARCHAR2(20) CONSTRAINT member_tbl_id_pk PRIMARY KEY,
  member_nickname VARCHAR2(50) CONSTRAINT member_tbl_nickname_nn NOT NULL,
  member_name VARCHAR2(50) CONSTRAINT member_tbl_name_nn NOT NULL,
  member_gender CHAR(1) CONSTRAINT member_tbl_gender_nn NOT NULL,
  member_email VARCHAR2(50) CONSTRAINT member_tbl_email_nn_un NOT NULL UNIQUE,
  member_phone VARCHAR2(13) CONSTRAINT member_tbl_phone_nn_un NOT NULL UNIQUE,
  member_birth DATE,
  member_zip VARCHAR2(5),
  member_address VARCHAR2(300),
  member_joindate DATE DEFAULT sysdate
);
 
DROP TABLE member_tbl;
 
CREATE TABLE member_tbl (
  member_id VARCHAR2(20),
  member_nickname VARCHAR2(50) CONSTRAINT member_tbl_nickname_nn NOT NULL,
  member_name VARCHAR2(50) CONSTRAINT member_tbl_name_nn NOT NULL,
  member_gender CHAR(1) CONSTRAINT member_tbl_gender_nn NOT NULL,
  member_email VARCHAR2(50) CONSTRAINT member_tbl_email_nn NOT NULL,
  member_phone VARCHAR2(13) CONSTRAINT member_tbl_phone_nn NOT NULL,
  member_birth DATE,
  member_zip VARCHAR2(5),
  member_address VARCHAR2(300),
  member_joindate DATE DEFAULT sysdate,
  CONSTRAINT member_tbl_id_pk PRIMARY KEY(member_id),
  CONSTRAINT member_tbl_email_un UNIQUE(member_email),
  CONSTRAINT member_tbl_phone_un UNIQUE(member_phone)
);
 
DROP TABLE member_tbl;
 
CREATE TABLE member_tbl (
  member_id VARCHAR2(20),
  member_nickname VARCHAR2(50),
  member_name VARCHAR2(50),
  member_gender CHAR(1),
  member_email VARCHAR2(50),
  member_phone VARCHAR2(13),
  member_birth DATE,
  member_zip VARCHAR2(5),
  member_address VARCHAR2(300),
  member_joindate DATE DEFAULT sysdate
);
 
ALTER TABLE member_tbl MODIFY (member_nickname CONSTRAINT member_tbl_nickname_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_name CONSTRAINT member_tbl_name_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_email CONSTRAINT member_tbl_email_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_phone CONSTRAINT member_tbl_phone_nn NOT NULL);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_id_pk PRIMARY KEY(member_id);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_email_un UNIQUE(member_email);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_phone_un UNIQUE(member_phone);
 
COMMENT ON COLUMN member_tbl.member_id IS '?????????';
COMMENT ON COLUMN member_tbl.member_nickname IS '??????';
COMMENT ON COLUMN member_tbl.member_name IS '??????';
COMMENT ON COLUMN member_tbl.member_gender IS '??????';
COMMENT ON COLUMN member_tbl.member_email IS '??????';
COMMENT ON COLUMN member_tbl.member_phone IS '?????????';
COMMENT ON COLUMN member_tbl.member_birth IS '????????????';
COMMENT ON COLUMN member_tbl.member_zip IS '????????????';
COMMENT ON COLUMN member_tbl.member_address IS '??????';
COMMENT ON COLUMN member_tbl.member_joindate IS '?????????';

-------------------------------------------------------------

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL primary key,
  password VARCHAR(60) NOT NULL,
  enabled number(1) DEFAULT 1
 );

CREATE TABLE user_roles (
  user_role_id number(11) NOT NULL,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT fk_username FOREIGN KEY (username) 
     REFERENCES users (username)
);
 
CREATE SEQUENCE user_roles_seq
	start with 1
	increment by 1
	maxvalue 99999
    nocycle; 

 -- remember-me ??????(csrf token) ????????? ?????? ????????? ??????
CREATE TABLE persistent_logins (
   username varchar(64) not null, 
   series varchar(64) primary key, 
   token varchar(64) not null, 
   last_used timestamp not null
);       

-- admin ??????
-- pw : 123456789
INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$10$US3HfE49gc5k.2nDwr/a9u1uCg6O8olzJZc5yERG.obR7xveqIHE2', 1);

INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_seq.nextval, 'admin', 'ROLE_ADMIN');

SELECT * FROM users WHERE username='admin';

delete user_roles;
delete users;