SET @SYSTEM_USER = 1;
SET @CREATE_DATE = '2014-01-01';

-- System User
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (@SYSTEM_USER, 'SYSTEM', 'SYSTEM', 'no-reply@ucdavis.edu');

-- People Data
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (2, 'Cameron', 'Jamison', 'camjam09@ucdavis.edu');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (3, 'Diana', 'Cox', 'dicox@ucdavis.edu');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (4, 'Dung', 'Phung', 'dkphung@ucdavis.edu');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (5, 'Jessica', 'Eisner', 'jdeisner@ucdavis.edu');
INSERT INTO Core.Person(Id, FirstName, LastName, Email) VALUES (6, 'Will', 'Oleksy', 'wroleksy@ucdavis.edu');