DELETE FROM USERS;

ALTER TABLE USERS
ADD USER_NAME NVARCHAR2(30) NOT NULL;

ALTER TABLE USERS
ADD PASSWORD NVARCHAR2(30) NOT NULL;