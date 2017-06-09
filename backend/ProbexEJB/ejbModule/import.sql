INSERT INTO permissions (id, name) VALUES (1,'standard'),(2,'pro'),(3,'admin');
INSERT INTO users (id, email, password, username, permission_id) VALUES (4,'fulano@online.com','3B��.��/0M���&��','fulano',1);
INSERT INTO users (id, email, password, username, permission_id) VALUES (5,'beltrano@online.com','���$%^�pfd<l�E','beltrano',2);
INSERT INTO users (id, email, password, username, permission_id) VALUES (6,'admin@online.com','!#/)zW��C�JJ��','admin',3);
INSERT INTO posts (id, content, date, title, author_id) VALUES (7,'content',now(),'title',4);
INSERT INTO votes (user_id, post_id) VALUES (6, 7);
INSERT INTO comments (id, content, date, author_id, post_id) VALUES (8,'voce tem razao!!!',now(),6,7);
INSERT INTO solutions (id, content, date, author_id, post_id) VALUES (9,'resolvido',now(),5,7);
INSERT INTO reports (id, description, author_id, post_id) VALUES (10,'nao gostei',4,7);
INSERT INTO posts (id, content, date, title, author_id) VALUES (11,'no one likes this post',now(),'unpopular post',6);
ALTER SEQUENCE hibernate_sequence RESTART WITH 101;