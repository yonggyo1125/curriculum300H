-- Mybook 스키마 생성 -- MySQL
CREATE TABLE Mybook (
  bookid      INTEGER,
  price       INTEGER 
);

-- Mybook 데이터 생성
INSERT INTO Mybook VALUES(1, 10000);
INSERT INTO Mybook VALUES(2, 20000);
INSERT INTO Mybook VALUES(3, NULL);

COMMIT;