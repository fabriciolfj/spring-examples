create table person (
    id BIGSERIAL primary key not null,
    name varchar(255) not null,
    last_name varchar(255) not null
);

create table address(
    id BIGSERIAL primary key not null,
    street varchar(255) not null,
    number int not null,
    city varchar(50) not null,
    state varchar(2) not null,
    person_id BIGSERIAL not null,
    constraint fk_person_address foreign key (person_id) references person(id)
);

create table post (
    id BIGSERIAL primary key not null,
    title varchar(255) not null,
    category varchar(255) not null,
    author_id BIGSERIAL not null
);

create table author (
    id BIGSERIAL primary key not null,
    name varchar(255) not null
);

CREATE TABLE VEHICLE (
VEHICLE_NO VARCHAR(10)NOT NULL,
COLOR VARCHAR(10),
WHEEL INT,
SEAT INT,
PRIMARY KEY (VEHICLE_NO)
);