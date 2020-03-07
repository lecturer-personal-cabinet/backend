INSERT INTO groups (id, title)
VALUES ('GRP1', '16-IT-1'),
       ('GRP2', '16-IT-2'),
       ('GRP3', '16-IT-3'),
       ('GRP4', '16-IT-4');
INSERT INTO users (id, type, first_name, last_name, patronymic, email, group_id)
VALUES ('USR1', 'FULL_TIME_STUDENT', 'Vladimir', 'Baklan', null, 'uladzimirbaklan@gmail.com', 'GRP1'),
       ('USR2', 'FULL_TIME_STUDENT', 'Beauden', 'Bautista', null, 'bb@gmail.com', 'GRP2'),
       ('USR3', 'FULL_TIME_STUDENT', 'Dani', 'Bird', null, 'db@gmail.com', 'GRP3'),
       ('USR4', 'FULL_TIME_STUDENT', 'Saqlain', 'Lowry', null, 'sl@gmail.com', 'GRP4');