CREATE TABLE web_user (
  id        BIGINT NOT NULL,
  login     VARCHAR(50),
  firstname VARCHAR(100),
  lastname  VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE SEQUENCE seq_user_id CACHE 10;

commit;
