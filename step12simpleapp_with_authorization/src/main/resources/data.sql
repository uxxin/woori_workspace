-- password 1111
INSERT INTO users VALUES (1, 'gugu', 'temp@gm.com', '$2a$10$lPZZzaFoD2XFCxdfW.GmuOcdJcBdqoUOkqZrmJDYjEcyHiKQd4z5K');
-- password 2222
INSERT INTO users VALUES (2, 'cucu', 'user@gm.com', '$2a$10$N0XMEG4Qf2XES1FZMDR1wezPVAzxB6bwPle.R3OL.nmuiOBm9tEKS');
-- password 3333
INSERT INTO users VALUES (3, 'mumu', 'admin@gm.com', '$2a$10$hCfnBuKkt.3NAf4qtd66cOwyQJ/6N8lrITVmtwNrOVloxLwZXf4uu');

INSERT INTO role VALUES (1, 'ROLE_TEMPORARY_USER');
INSERT INTO role VALUES (2, 'ROLE_USER');
INSERT INTO role VALUES (3, 'ROLE_ADMIN');

INSERT INTO authority VALUES (1, 'COMMUNICATION_AUTHORITY');
INSERT INTO authority VALUES (2, 'WORK_AUTHORITY');
INSERT INTO authority VALUES (3, 'TASK_AUTHORITY');
INSERT INTO authority VALUES (4, 'CONFIG_AUTHORITY');

INSERT INTO user_role VALUES (1, 1);

INSERT INTO user_role VALUES (2, 1);
INSERT INTO user_role VALUES (2, 2);

INSERT INTO user_role VALUES (3, 1);
INSERT INTO user_role VALUES (3, 2);
INSERT INTO user_role VALUES (3, 3);

INSERT INTO role_authority VALUES (1, 1);

INSERT INTO role_authority VALUES (2, 2);
INSERT INTO role_authority VALUES (2, 3);

INSERT INTO role_authority VALUES (3, 4);

-- Product
INSERT INTO product (`id`, `name`, `price`) VALUES ('1', 'Black noodle', '10');