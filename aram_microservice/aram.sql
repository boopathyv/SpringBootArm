drop database if exists aram;

-- create a database
create database aram;


-- make database as default
use aram;

create TABLE IF NOT EXISTS aram.prelims_current_affiars (
prelims_id int(20) primary key,
prelims_date date not null,
prelims_location varchar(255),
prelims_heading varchar(500),
prelims_short_description varchar(1000),
prelims_description blob,
created_by varchar(100),
modified_by varchar(100),
created_date timestamp, 
modified_date timestamp default current_timestamp
);

create TABLE IF NOT EXISTS aram.mains_current_affiars (
mains_id int(20) primary key,
mains_date date not null,
mains_location varchar(255),
mains_heading varchar(500),
mains_short_description varchar(1000),
mains_description blob,
created_by varchar(100),
modified_by varchar(100),
created_date timestamp,
modified_date timestamp default current_timestamp
);

CREATE UNIQUE INDEX prelims_date_index
on aram.prelims_current_affiars (prelims_date);

CREATE UNIQUE INDEX mains_date_index
on aram.mains_current_affiars (mains_date);

create TABLE IF NOT EXISTS aram.prelims_quiz (
prelims_quiz_id int(20) primary key,
prelims_quiz_date date,
prelims_question varchar(2500),
optionA varchar(150),
optionB varchar(150),
optionC varchar(150),
optionD varchar(150),
optionE varchar(150),
answer varchar(150),
explanation varchar(2500),
created_by varchar(100),
modified_by varchar(100),
created_date timestamp,
modified_date timestamp default current_timestamp
);

create TABLE IF NOT EXISTS aram.mains_quiz (
mains_quiz_id int(20) primary key,
mains_quiz_date date,
mains_quiz_location varchar(255),
mains_quiz_heading varchar(500),
mains_quiz_short_description varchar(1000),
mains_quiz_description blob,
created_by varchar(100),
modified_by varchar(100),
created_date timestamp,
modified_date timestamp default current_timestamp
);		

CREATE UNIQUE INDEX prelims_quiz_date_index
on aram.prelims_quiz (prelims_quiz_date);

CREATE UNIQUE INDEX mains_quiz_date_index
on aram.mains_quiz (mains_quiz_date);

create TABLE IF NOT EXISTS aram.subscribe(
subscribe_id int(11) primary key,
email_id  varchar(100) not null,
subscribed_date timestamp,
active_flag char(1) default 'Y'
);

CREATE UNIQUE INDEX subscribe_index
on aram.subscribe (email_id);


create TABLE IF NOT EXISTS aram.join_us(
join_us_id int(11) primary key,
email_id varchar(100) not null,
mobile_number int(10) not null,
user_name varchar(100),
message varchar(2000)
);

create table `user_role`(
	role_id int(5) primary key,
    role varchar(150) NOT NULL
    
);

create table `students`(
	student_id int(11) primary key,
    student_name varchar(150) NOT NULL,
    stream varchar(150) NOT NULL,
    email_id varchar(150) NOT NULL    
);

create table `staffs`(
	staff_id int(11) primary key,
    staff_name varchar(150) NOT NULL,
    stream varchar(150) NOT NULL,
    email_id varchar(150) NOT NULL
);

create TABLE IF NOT EXISTS `fortnight_compilation` (
fortnight_id int(20) primary key,
fortnight_date date,
fortnight_location varchar(255),
fortnight_heading varchar(500),
fortnight_short_description varchar(1000),
fortnight_description blob,
created_by varchar(100),
modified_by varchar(100),
created_date timestamp,
modified_date timestamp default current_timestamp
);	

create table user_login(
	user_login_id int(11) primary key,
    user_identity varchar(500) NOT NULL,
    role_id int(5),
    created_by varchar(100),
	modified_by varchar(100),
	created_date timestamp,
	modified_date timestamp default current_timestamp,
    CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
    
);

create TABLE IF NOT EXISTS aram.online_test (
question_id int(20) primary key,
test_id int(20) not null,
online_test_question varchar(2500),
optionA varchar(150),
optionB varchar(150),
optionC varchar(150),
optionD varchar(150),
answer varchar(150),
explanation varchar(2500),
created_by varchar(100),
modified_by varchar(100),
created_date timestamp,
modified_date timestamp default current_timestamp
);

create TABLE IF NOT EXISTS aram.user_test_detail (
user_test_id int(20) primary key,
user_login_id int(20) not null,
test_id int(20) not null,
attended_question int(20),
unattended_question int(20),
correct_answer int(20),
wrong_answer int(20),
attended_id int(20),
total_marks long,
created_by varchar(100) ,
modified_by varchar(100),
created_date timestamp,
modified_date timestamp default current_timestamp,
CONSTRAINT `user_login_test_fk` FOREIGN KEY (`user_login_id`) REFERENCES `user_login` (`user_login_id`)
);

create TABLE IF NOT EXISTS aram.test_attempt_detail(
	test_attempt_detail_id int(20) primary key,
    attended_question_id int(20),
    attended_answer varchar(10),
    user_login_id int(20) not null,
    test_id int(20) not null,
    attempt_id int(20),
    CONSTRAINT `user_attempt_login_fk` FOREIGN KEY (`user_login_id`) REFERENCES `user_login` (`user_login_id`)
);

create TABLE IF NOT EXISTS aram. latest_news (
latest_news_id int(11),
news varchar(1000),
link varchar(1000),
active_flag boolean,
created_by varchar(100),
modified_by varchar(100),
created_date timestamp, 
modified_date timestamp default current_timestamp
);