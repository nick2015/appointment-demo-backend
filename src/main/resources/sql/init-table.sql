drop table if exists appointment;

CREATE TABLE appointment (
  ID BIGINT(20) PRIMARY KEY,
  USER_ID VARCHAR(20),
  SUBJECT varchar(200),
  START_TIME datetime,
  END_TIME datetime
);