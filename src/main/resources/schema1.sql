create type role as enum ('admin', 'moderator', 'user');

create table if not exists Users (
    id bigserial PRIMARY key,
    username VARCHAR(50) not null,
    password varchar(32) not null,
    birthdate date,
    timezone real default 0,
    description text,
    email VARCHAR(50),
    phone_number varchar(13),
    balance real default 0,
    is_active boolean default true,
    created_at timestamp not null,
    user_role role not null DEFAULT 'user'
    );

create table if not exists Mentor_info (
    id bigserial PRIMARY KEY,
    user_id bigserial not null,
    subject_area VARCHAR(100) not null,
    price real,
    description text,
    education VARCHAR(100),
    work_expirience VARCHAR(100),
    rating real check (rating <= 5),
    attendance INTEGER check (attendance >= 0 AND attendance <= 100),
    number_of_students INTEGER check (number_of_students >= 0) DEFAULT 0,
    number_of_lessons INTEGER check (number_of_lessons >= 0) DEFAULT 0,
    number_of_cancelled_lessons INTEGER check (number_of_cancelled_lessons >= 0) DEFAULT 0,
    created_at timestamp not null,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
    );

create table if not exists Mentee_info (
    id bigserial PRIMARY KEY,
    user_id bigserial not null,
    attendance INTEGER check (attendance >= 0 AND attendance <= 100),
    number_of_students INTEGER check (number_of_students >= 0) DEFAULT 0,
    number_of_lessons INTEGER check (number_of_lessons >= 0) DEFAULT 0,
    number_of_cancelled_lessons INTEGER check (number_of_cancelled_lessons >= 0) DEFAULT 0,
    created_at timestamp not null,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
    );

CREATE TABLE IF not EXISTS Lessons (
    id bigserial PRIMARY KEY,
    schedule INTERVAL NOT NULL,
    created_at timestamp not null,
    mentor_id bigserial not null,
    mentee_id bigserial not null,
    foreign key (mentor_id) references Mentor_info(id),
    foreign key (mentee_id) references Mentee_info(id),
    CONSTRAINT one_time_for_mentor UNIQUE(mentor_id, schedule),
    CONSTRAINT one_time_for_mentee UNIQUE(mentee_id, schedule)
    );

