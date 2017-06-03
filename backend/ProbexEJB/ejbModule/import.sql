INSERT INTO permissions (id, name) VALUES (1,'standard'),(2,'pro'),(3,'admin');
INSERT INTO users (id, email, password, username, permission_id) VALUES (4,'fulano@online.com','fulano','fulano',1);
INSERT INTO users (id, email, password, username, permission_id) VALUES (5,'admin@online.com','admin','admin',3);
INSERT INTO posts (id, content, date, title, author_id) VALUES (6,'content',now(),'title',5);
INSERT INTO votes (user_id, post_id) VALUES (4, 6);
ALTER SEQUENCE hibernate_sequence RESTART WITH 101;