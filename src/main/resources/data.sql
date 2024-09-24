INSERT INTO Role Values(1,'NORMAL');
INSERT INTO Role Values(2,'CONTENT_WRITER');
INSERT INTO Role Values(3,'ADMIN');
INSERT INTO user  VALUES (1, '1990-01-01','Admin@test.com','Admin Admin','$2a$10$nHEHvuMLExiijqkAZGq5jOaGb2oN3QdN2CkJvLGYcS24rVM9c4UEO','' ,3);
ALTER TABLE user AUTO_INCREMENT = 3;
commit ;
