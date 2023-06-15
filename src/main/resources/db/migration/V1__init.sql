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

CREATE TABLE COURSE (
ID BIGSERIAL primary key not null,
TITLE varchar(100) not null,
BEGIN_DATE date,
END_DATE date,
FEE INT
);

CREATE TABLE BOOK (
    ISBN         VARCHAR(50)    NOT NULL,
    BOOK_NAME    VARCHAR(100)   NOT NULL,
    PRICE        INT,
    PRIMARY KEY (ISBN)
);

CREATE TABLE BOOK_STOCK (
    ISBN     VARCHAR(50)    NOT NULL,
    STOCK    INT            NOT NULL,
    PRIMARY KEY (ISBN),
    CONSTRAINT positive_stock CHECK (STOCK >= 0)
);

CREATE TABLE ACCOUNT (
    USERNAME    VARCHAR(50)    NOT NULL,
    BALANCE     INT            NOT NULL,
    PRIMARY KEY (USERNAME),
    CONSTRAINT positive_balance CHECK (BALANCE >= 0)
);

INSERT INTO BOOK( ISBN, BOOK_NAME, PRICE ) VALUES( '0001', 'The First Book', 30);
INSERT INTO BOOK_STOCK(ISBN, STOCK) VALUES('0001', 10) ;
INSERT INTO ACCOUNT(USERNAME, BALANCE) VALUES('user1',20);