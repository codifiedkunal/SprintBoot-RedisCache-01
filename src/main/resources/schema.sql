create table employee
(
   id identity not null,
   first_name varchar(20) not null,
   last_name varchar(20) not null,
   sex char(1) not null,
   dob date,
   salary number(15),
   primary key(id)
);