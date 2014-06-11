drop schema if exists inquizition;
create schema inquizition;
use inquizition;

create table users(
	id int not null auto_increment primary key,
	name varchar(20) unique,
	password varchar(40),
	admin boolean default false
);

create table quizzes(
	id int not null auto_increment primary key,
	name varchar(64),
	descript text,
	time_created timestamp,
	creator_id int,
	times_taken int default 0,
	foreign key(creator_id) 
		references users(id)
		on delete set null
);

create table questions(
	id int not null auto_increment primary key,
	question text,
	quiz_id int,
	foreign key(quiz_id) 
		references quizzes(id)
		on delete cascade 
);

create table answers(
	id int not null auto_increment primary key,
	answer text,
	question_id int, 
	foreign key(question_id) 
		references questions(id)
		on delete cascade 
);

create table friendship(
	id int not null auto_increment primary key,
	first_id int,
	second_id int,
	foreign key(first_id) 
		references users(id)
		on delete cascade,
	foreign key(second_id) 
		references users(id)
		on delete cascade	
);

create table history(
	id int not null auto_increment primary key,
	user_id int,
	quiz_id int,
	time_elapsed double, -- in milliseconds
	tdate datetime,
	foreign key(user_id) 
		references users(id)
		on delete cascade,
	foreign key(quiz_id) 
		references quizzes(id)
		on delete cascade
);

create table messages(
	id int not null auto_increment primary key,
	from_id int,
	to_id int,
	subject text,
	message text,
	dtime datetime,
	unread bool default true,
	foreign key(from_id) 
		references users(id)
		on delete cascade,
	foreign key(to_id) 
		references users(id)
		on delete cascade
);

create table wall(
	id int not null auto_increment primary key,
	adminID int not null,
	foreign key(adminId)
		references users(id)
		on delete cascade,
	message text,
	dtime datetime 
);

create trigger upd_history after insert on history
	for each row
		update quizzes 
			set quizzes.times_taken = quizzes.times_taken + 1 
			where new.quiz_id = quizzes.id;