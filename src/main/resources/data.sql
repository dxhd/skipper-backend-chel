insert into users (
                   balance,
                   birthdate,
                   description,
                   email,
                   password,
                   phone_number,
                   time_zone,
                   user_picture,
                   username
                   )
values (
        100.03,
        '09.09.2000',
        'some desc1',
        'test1@mail.ru',
        'passss',
        '89005553535',
        3,
        'pictures/some_path_to_picture.jpg',
        'dxhd'
        );
insert into users (
    balance,
    birthdate,
    description,
    email,
    password,
    phone_number,
    time_zone,
    user_picture,
    username
)
values (
           222.03,
           '10.09.2000',
           'some desc2',
           'test2@mail.ru',
           'dieantwoord',
           '4511911213',
           2,
           'pictures/awesome_me.jpg',
           'borba'
       );
insert into users (description, email, phone_number, password)
values ('some desc1', 'test2@mail.ru', 'jjk', 'pass21s');
insert into users (description, email, phone_number, password)
values ('some desc1', 'test3@mail.ru', 'chopper', 'pa23ssss');
insert into users (description, email, phone_number, password)
values ('some desc1', 'test4@mail.ru', 'cactus', 'passs432s');

INSERT into mentor_info (
                         certificates,
                         description,
                         education,
                         price,
                         rating,
                         speciality,
                         work_experience,
                         user_id
                         )
VALUES (
        'certificate1_certificate2',
        'i am a professional footbal watcher',
        'susu4',
        100,
        4.2,
        'programming',
        'It aint much but it is honest work',
        2
        );
INSERT into mentor_info (education, user_id)
VALUES ('susu4', 3);

INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('FINISHED', 1, 1, '2022-09-12');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('CANCELLED', 1, 1, '12-09-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('FINISHED', 1, 1, '12-09-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('FINISHED', 1, 1, '12-01-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('PLANNED', 1, 1, '10-09-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('PLANNED', 1, 1, '12-09-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('PLANNED', 1, 1, '2-09-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('IN_PROGRESS', 1, 1, '12-11-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('CANCELLED', 1, 2, '12-08-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('FINISHED', 1, 2, '09-15-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('PLANNED', 1, 2, '11-11-2022');
INSERT INto lessons (status, mentee_id, mentor_id, date_of_lesson)
values ('IN_PROGRESS', 1, 2, '12-10-2022');
