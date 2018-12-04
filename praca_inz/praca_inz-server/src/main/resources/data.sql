TRUNCATE TABLE DEVICE CASCADE;
TRUNCATE TABLE WAREHOUSE CASCADE;
TRUNCATE TABLE USERDATA CASCADE;
TRUNCATE TABLE OFFICE CASCADE;
TRUNCATE TABLE DEPARTMENT CASCADE;
TRUNCATE TABLE COMPANY CASCADE;
TRUNCATE TABLE ADDRESS CASCADE;
TRUNCATE TABLE OAUTH_CLIENT_DETAILS CASCADE;
TRUNCATE TABLE AUTHORITY CASCADE;
TRUNCATE TABLE ADDRESS CASCADE;
TRUNCATE TABLE USER_ CASCADE;
TRUNCATE TABLE USERS_AUTHORITIES CASCADE;


INSERT INTO ADDRESS(ID, CITY, BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (1, 'Łódź', 1, 'Street X', '12-341');
INSERT INTO ADDRESS(ID, CITY,  BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (2, 'Warszawa', 2, 'Street Y', '12-342');
INSERT INTO ADDRESS(ID, CITY,  BUILDING_NUMBER, STREET, ZIP_CODE,FLAT_NUMBER) VALUES (3, 'Łódź',  3, 'Street Z', '12-343',2);
INSERT INTO ADDRESS(ID, CITY,  BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (4, 'Łódź',  4, 'Street XX', '12-344');
INSERT INTO ADDRESS(ID, CITY,  BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (5, 'Łódź',  5, 'Street YY', '12-345');
INSERT INTO ADDRESS(ID, CITY,  BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (6, 'Łódź',  6, 'Street ZZ', '12-346');
INSERT INTO ADDRESS(ID, CITY,  BUILDING_NUMBER, STREET, ZIP_CODE) VALUES (7, 'Łódź',  7, 'Street XXX', '12-347');

INSERT INTO COMPANY(ID, NAME, MAIN_OFFICE_ADRESS_ID,DESCRIPTION) VALUES (1, 'Transition Technologies',1, 'OPIS');


INSERT INTO DEPARTMENT(ID, NAME, COMPANY_ID,DESCRIPTION) VALUES (1, 'Sales & Marketing', 1,'OPIS');
INSERT INTO DEPARTMENT(ID, NAME, COMPANY_ID) VALUES (2, 'Research & Development', 1);
INSERT INTO DEPARTMENT(ID, NAME, COMPANY_ID) VALUES (3, 'Administration', 1);
INSERT INTO DEPARTMENT(ID, NAME, COMPANY_ID) VALUES (4, 'Human Resources', 1);
INSERT INTO DEPARTMENT(ID, NAME, COMPANY_ID) VALUES (5, 'Sales & Marketing', 1);


INSERT INTO OFFICE(ID, NAME, ADDRESS_ID, DEPARTMENT_ID,DESCRIPTION) VALUES (1, 'Office of S&M Boston', 4, 1,'OPIS');
INSERT INTO OFFICE(ID, NAME, ADDRESS_ID, DEPARTMENT_ID) VALUES (2, 'Office of S&M New York', 5, 1);
INSERT INTO OFFICE(ID, NAME, ADDRESS_ID, DEPARTMENT_ID) VALUES (3, 'Office of R&D Boston', 6, 2);
INSERT INTO OFFICE(ID, NAME, ADDRESS_ID, DEPARTMENT_ID) VALUES (4, 'Office of A Los Angeles', 7, 3);


INSERT INTO USERDATA(ID, NAME, SURNAME, EMAIL,ADDRESS_ID, OFFICE_ID) VALUES (1, 'John', 'William','email@email.com', 1, 1);
INSERT INTO USERDATA(ID, NAME, SURNAME, ADDRESS_ID, OFFICE_ID) VALUES (2, 'Robert', 'James', 2, 2);
INSERT INTO USERDATA(ID, NAME, SURNAME, ADDRESS_ID, OFFICE_ID) VALUES (3, 'Donald', 'Tyler', 3, 3);


-- INSERT INTO WAREHOUSE(ID, NAME, OFFICE_ID) VALUES (1,'MAIN WAREHOUSE',1);
--
--
-- INSERT INTO DEVICE(ID, SERIAL_NUMBER, WAREHOUSE_ID) VALUES (1, 'XYZ10ABC', 1);
-- INSERT INTO DEVICE(ID, SERIAL_NUMBER, WAREHOUSE_ID) VALUES (2, 'XYZ11ABC', 1);
-- INSERT INTO DEVICE(ID, SERIAL_NUMBER, WAREHOUSE_ID) VALUES (3, 'XYZ12ABC', 1);
-- INSERT INTO DEVICE(ID, SERIAL_NUMBER, WAREHOUSE_ID) VALUES (4, 'XYZ13ABC', 1);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',
	/*spring-security-oauth2-read-client-password1234*/'$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
	'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
	/*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
	'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO AUTHORITY(ID, NAME) VALUES (1, 'COMPANY_CREATE');
INSERT INTO AUTHORITY(ID, NAME) VALUES (2, 'COMPANY_READ');
INSERT INTO AUTHORITY(ID, NAME) VALUES (3, 'COMPANY_UPDATE');
INSERT INTO AUTHORITY(ID, NAME) VALUES (4, 'COMPANY_DELETE');

INSERT INTO AUTHORITY(ID, NAME) VALUES (5, 'DEPARTMENT_CREATE');
INSERT INTO AUTHORITY(ID, NAME) VALUES (6, 'DEPARTMENT_READ');
INSERT INTO AUTHORITY(ID, NAME) VALUES (7, 'DEPARTMENT_UPDATE');
INSERT INTO AUTHORITY(ID, NAME) VALUES (8, 'DEPARTMENT_DELETE');

INSERT INTO AUTHORITY(ID, NAME) VALUES (9, 'ROLE_COMPANY_READER');

INSERT INTO USER_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (1, 'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USER_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (2, 'reader', /*reader1234*/'$2a$08$dwYz8O.qtUXboGosJFsS4u19LHKW7aCQ0LXXuNlRfjjGKwj5NfKSe', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USER_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (3, 'modifier', /*modifier1234*/'$2a$08$kPjzxewXRGNRiIuL4FtQH.mhMn7ZAFBYKB3ROz.J24IX8vDAcThsG', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USER_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (4, 'reader2', /*reader1234*/'$2a$08$vVXqh6S8TqfHMs1SlNTu/.J25iUCrpGBpyGExA.9yI.IlDRadR6Ea', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 3);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 4);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 5);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 6);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 7);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 8);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 9);

INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (2, 2);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (2, 6);

INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 3);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 7);

INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (4, 9);
