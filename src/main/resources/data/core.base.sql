SET @CREATE_DATE = '2014-01-01';

-- admin user
SET @ADMIN_USER = 1;
INSERT INTO Auth.User(Id, Username, Password, AccountEnabled, AccountExpired, AccountLocked, CredentialsExpired ) VALUES (@ADMIN_USER, 'admin', '$2a$10$VFyiojcn4PUh2FVgFsb6Ie8hPh/GzMh4hiAL3551sJ/xELDlNfwP2', 1, 0, 0, 0);
INSERT INTO Auth.UserAuthority(User, Authority) VALUES (@ADMIN_USER, 'ROLE_ADMIN');
INSERT INTO Auth.UserAuthority(User, Authority) VALUES (@ADMIN_USER, 'ROLE_USER');

-- user
SET @USER = 2;
INSERT INTO Auth.User(Id, Username, Password, AccountEnabled, AccountExpired, AccountLocked, CredentialsExpired ) VALUES (@USER, 'user', '$2a$10$hTt2wR90jREkIXF4jAsFQum635MyoBNz5YEzGVH.4m61Gbxu5ASX.', 1, 0, 0, 0);
INSERT INTO Auth.UserAuthority(User, Authority) VALUES (@USER, 'ROLE_USER');

-- System User
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (@ADMIN_USER, 'SYSTEM', 'SYSTEM', 'no-reply@mysite.com');

-- People Data
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (2, 'Pradeep', 'Haldiya', 'pradeep.haldiya@gmail.com');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (3, 'Priya', 'Haldiya', 'priya.haldiya@gmail.com');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (4, 'Priyaan', 'Haldiya', 'priyaan.haldiya@gmail.com');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (5, 'Jai', 'Sharma', 'jai.sharma@gmail.com');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (6, 'Rajeev', 'Upadhayay', 'rajeev.upadhayay@gmail.com');

