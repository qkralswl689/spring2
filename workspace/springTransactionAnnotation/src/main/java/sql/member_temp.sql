-- 회원정보 테이블 : DDL(create, drop, alter)

drop table member_temp2;

CREATE TABLE member_temp2 (
	id varchar2(20) primary key,
	pw varchar2(20) not null,
	name varchar2(50) not null,
	address varchar2(300),
	joindate date default sysdate
);

-- DML

insert into member_temp2 values 
	('oj1234', '12345678', '홍길동', '서울 구로구 구로동', sysdate);

update member_temp2 
   set address='서울 구로구 구일동',
       pw='11111111'
   where id='oj1234';    
              
select * from member_temp2;	

select * from member_temp2
   where id='oj1234';
   
delete member_temp2;   

delete member_temp2 where id='oj1234';