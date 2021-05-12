CREATE OR REPLACE PROCEDURE dummy_member_gen_proc
IS
BEGIN
 
    -- FOR i IN 1..10 LOOP
    FOR i IN 1..100 LOOP
    
        INSERT INTO member_tbl VALUES
        ('goodee_' || (1000+i),
         '별명',
         '김' || (100+i),
         'm',
         'goodee_' || i || '@abcd.com',
         '010-1234-' || (1000+i),
         '1990-1-1',  
         '12345',  
         '서울 금천구 가산대로*구디아카데미',
         SYSDATE);
                
        INSERT INTO users VALUES 
	    ('goodee_' || (1000+i), 
		 '',
		 1);
    
  		INSERT INTO user_roles VALUES 
  		(user_roles_seq.nextval,
   		'goodee_' || (1000+i),  
   		'ROLE_USER');  
         
     END LOOP;
 
    COMMIT;    
END;
/