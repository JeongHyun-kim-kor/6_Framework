
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;


CREATE USER board_user IDENTIFIED BY board1234;

GRANT RESOURCE, CONNECT TO board_user;

ALTER USER	board_user 	DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM;