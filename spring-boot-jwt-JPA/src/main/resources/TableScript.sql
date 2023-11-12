drop database if exists user_db;

create database user_db;

use user_db;


CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
);

CREATE TABLE user_appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
     FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE
);

insert into user_appointment values (1001, 'Steven Martin');
insert into user_appointment values (1002, 'Kevin Nelson');
insert into user_appointment values (1003, 'Jill Tribbiani');
insert into user_appointment values (1004, 'Chan Mathew');
insert into user_appointment values (1005, 'Jill Mathew');

CREATE TABLE coach (
    id INT AUTO_INCREMENT PRIMARY KEY,
    coach_name VARCHAR(255),
    speciality VARCHAR(255)
);

insert into coach values(12346, 'Ross','MentalHealth');
insert into coach values(12347, 'Rachel','Depression');
insert into coach values(12348, 'Chandler','Orthoritis');
insert into coach values(12349, 'Joey','MentalHealth');
insert into coach values(12350, 'Phoebe','Diabetes');
insert into coach values(12351, 'Monica','Diabetes');

CREATE TABLE appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    
    coach_name VARCHAR(255),
    date_time_slot DATETIME,
    username INT,
    FOREIGN KEY (user_name) REFERENCES user(username),
    FOREIGN KEY (coach_name) REFERENCES coach(coach_name)
);

insert into appointment values(1234, 'Ross',sysdate()+interval 3400 day,1001);
insert into appointment values(1235, 'Rachel',sysdate()+interval 4560 day,1001);
insert into appointment values(1236, 'Chandler',sysdate()+interval 1160 day,1001);
insert into appointment values(1237, 'Joey',sysdate()+interval 5660 day,1002);
insert into appointment values(1238, 'Phoebe',sysdate()+interval 5640 day,1003);
insert into appointment values(1239, 'Monica',sysdate()+interval 5620 day,1003);



commit;


select * from userAppointment;
select * from appointment;
